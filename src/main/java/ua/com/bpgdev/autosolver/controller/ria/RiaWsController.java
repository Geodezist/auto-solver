package ua.com.bpgdev.autosolver.controller.ria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.service.fact.SourceCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;
import ua.com.bpgdev.autosolver.util.ProgressStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Controller
@CrossOrigin(origins = "*")
public class RiaWsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SimpMessagingTemplate messagingTemplate;
    private RiaSearchResultService riaSearchResultService;
    private RiaCarService riaCarService;
    private SourceCarService sourceCarService;

    private Map<String, ProgressStatus> sessionsProgress = new ConcurrentHashMap<>();

    @Autowired
    public RiaWsController(RiaSearchResultService riaSearchResultService,
                           SourceCarService sourceCarService,
                           RiaCarService riaCarService,
                           SimpMessagingTemplate messagingTemplate) {
        this.riaSearchResultService = riaSearchResultService;
        this.sourceCarService = sourceCarService;
        this.riaCarService = riaCarService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/cardata/{sessionId}")
    public void /*SseEmitter*/ saveAllCarsWs(@DestinationVariable("sessionId") String sessionId,
                                             String queryString) throws InterruptedException, ExecutionException {
        logger.debug("WS WORKED!!! SessionId = {}, queryString = {}", sessionId, queryString);

        String cleanQueryString = queryString.replace("/savecars", "");
        List<Integer> carIds = new ArrayList<>(riaSearchResultService.getSearchResult(cleanQueryString));

        if (queryString.endsWith("/savecars")) {
            List<Integer> existingCarIds = sourceCarService.findAllByCarIdIn(carIds);
            List<Integer> absentCarIds = new ArrayList<>(carIds);
            absentCarIds.removeAll(existingCarIds);

            ProgressStatus progressStatus = new ProgressStatus();
            sessionsProgress.put(sessionId, progressStatus);
            Callable<List<RiaCarDTO>> taskRiaCarDTOs = () -> riaCarService.getAll(absentCarIds, progressStatus);

            List<Callable<List<RiaCarDTO>>> taskList = Collections.singletonList(taskRiaCarDTOs);
            ExecutorService movieEnricherExecutorService = Executors.newFixedThreadPool(3);
            List<Future<List<RiaCarDTO>>> futureList = movieEnricherExecutorService.invokeAll(taskList);

            List<RiaCarDTO> riaCarDTOs = futureList.get(0).get();

            sourceCarService.saveAllDTO(riaCarDTOs);
        }
        messagingTemplate.convertAndSend("/auto-solver-websocket-broker/" + sessionId, sourceCarService.getAllByIds(carIds));
    }

    @MessageMapping("/cardata/progress/{sessionId}")
    public void saveAllCarsWsProgress(@DestinationVariable("sessionId") String sessionId) {
        ProgressStatus progressStatus = sessionsProgress.get(sessionId);
        if (progressStatus == null) {
            progressStatus = new ProgressStatus();
        }

        logger.info("saveAllCarsWsProgress - {} ", sessionId);
        messagingTemplate.convertAndSend("/auto-solver-websocket-broker/progress/" + sessionId, progressStatus);
    }

}
