package ua.com.bpgdev.autosolver.controller.ria;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.com.bpgdev.autosolver.dto.ria.SearchRequestDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.service.fact.SourceCarService;
import ua.com.bpgdev.autosolver.service.filter.AdditionalFilterService;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;
import ua.com.bpgdev.autosolver.util.ProgressStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class RiaWsController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RiaSearchResultService riaSearchResultService;
    private final SourceCarService sourceCarService;
    private final AdditionalFilterService additionalFilterService;

    private final Map<String, ProgressStatus> sessionsProgress = new ConcurrentHashMap<>();

    @MessageMapping("/cardata/{sessionId}")
    public void /*SseEmitter*/ saveAllCarsWs(@DestinationVariable("sessionId") String sessionId,
                                             SearchRequestDTO searchRequest) {

        log.debug("WS WORKED!!! SessionId = {}, searchRequest = {}", sessionId, searchRequest.toString());

        List<Integer> carIds = new ArrayList<>(riaSearchResultService.getSearchResult(searchRequest));
        ProgressStatus progressStatus = new ProgressStatus();
        sessionsProgress.put(sessionId, progressStatus);

        sourceCarService.saveAllFromRia(searchRequest, progressStatus);

        Specification<SourceCar> filter = additionalFilterService.getAdditionalFilter(searchRequest.getAddFilter());
        messagingTemplate.convertAndSend("/auto-solver-websocket-broker/" + sessionId,
                sourceCarService.getAllByIdsAndFilter(carIds, filter)
        );
        sessionsProgress.remove(sessionId);
    }

    @MessageMapping("/cardata/progress/{sessionId}")
    public void saveAllCarsWsProgress(@DestinationVariable("sessionId") String sessionId) {
        ProgressStatus progressStatus = sessionsProgress.get(sessionId);
        if (progressStatus == null) {
            progressStatus = new ProgressStatus();
        }

        log.info("saveAllCarsWsProgress - sessionId = {}, progressStatus = {} ", sessionId, progressStatus.getCurrent());
        messagingTemplate.convertAndSend("/auto-solver-websocket-broker/progress/" + sessionId, progressStatus);
    }

}
