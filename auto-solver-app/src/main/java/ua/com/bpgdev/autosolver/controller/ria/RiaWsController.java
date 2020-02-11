package ua.com.bpgdev.autosolver.controller.ria;

import lombok.AllArgsConstructor;
import org.hibernate.type.SpecialOneToOneType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SearchCriteria;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SearchOperation;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.specification.SourceCarSpecification;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.service.fact.SourceCarService;
import ua.com.bpgdev.autosolver.service.filter.AdditionalFilterService;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;
import ua.com.bpgdev.autosolver.util.ProgressStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RiaWsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SimpMessagingTemplate messagingTemplate;
    private final RiaSearchResultService riaSearchResultService;
    private final RiaCarService riaCarService;
    private final SourceCarService sourceCarService;
    private final AdditionalFilterService additionalFilterService;

    private Map<String, ProgressStatus> sessionsProgress = new ConcurrentHashMap<>();

    @MessageMapping("/cardata/{sessionId}")
    public void /*SseEmitter*/ saveAllCarsWs(@DestinationVariable("sessionId") String sessionId,
                                             String queryString) throws InterruptedException, ExecutionException {
        logger.debug("WS WORKED!!! SessionId = {}, queryString = {}", sessionId, queryString);

        String cleanQueryString = queryString.replaceAll("(/add_filter.*)|(/savecars.*)", "");
        String additionalFilterName = queryString
                .replaceAll(".*(/add_filter=)", "")
                .replaceAll("(/savecars.*)", "");
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
        Specification<SourceCar> filter = additionalFilterService.getAdditionalFilter(additionalFilterName);

        messagingTemplate.convertAndSend("/auto-solver-websocket-broker/" + sessionId,
                sourceCarService.getAllByIdsAndFilter(carIds, filter)
        );
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
