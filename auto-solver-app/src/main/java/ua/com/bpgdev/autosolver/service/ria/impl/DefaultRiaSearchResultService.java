package ua.com.bpgdev.autosolver.service.ria.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaRestDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaSearchResultDTO;
import ua.com.bpgdev.autosolver.dto.ria.SearchRequestDTO;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultRiaSearchResultService implements RiaSearchResultService {
    private final RiaRestDao riaRestDao;
    private final ObjectMapper objectMapper;

    @Override
    public Set<Integer> getSearchResult(SearchRequestDTO searchRequest) {
        RiaSearchResultDTO riaSearchResultDTO = getSearchResultFromRia(searchRequest);
        int totalCountOfCarIds = riaSearchResultDTO.getTotalCount();

        int pageSize = riaSearchResultDTO.getPageSize();
        Set<Integer> carIds = new HashSet<>(riaSearchResultDTO.getCarIds());

        if (totalCountOfCarIds <= MAX_TOTAL_COUNT_OF_CAR_IDS) {
            for (int page = 1; page * pageSize < totalCountOfCarIds; page++) {
                searchRequest.setPage(page);
                RiaSearchResultDTO nextRiaSearchResultDTO = getSearchResultFromRia(searchRequest);
                carIds.addAll(nextRiaSearchResultDTO.getCarIds());
            }
        } else {
            log.info("Too many car ids returned - {}, returned first {} carIds!", totalCountOfCarIds, pageSize);
        }
        log.debug("Got {} car ids from {}", carIds.size(), searchRequest.toString());
        return carIds;
    }

    private RiaSearchResultDTO getSearchResultFromRia(SearchRequestDTO searchRequest) {
        try {
            JsonNode jsonNode = riaRestDao.getSearchResult(searchRequest);
            int page = jsonNode.at("/additional_params/page").intValue();
            List<Integer> carIds = objectMapper.readValue(jsonNode.at("/result/search_result/ids").traverse(),
                    new TypeReference<>() {
                    });
            int totalCount = jsonNode.at("/result/search_result/count").intValue();
            int pageSize = jsonNode.at("/result/additional/search_params/all/countpage").intValue();
            return RiaSearchResultDTO.builder()
                    .queryString("searchRequest")
                    .page(page)
                    .carIds(carIds)
                    .totalCount(totalCount)
                    .pageSize(pageSize)
                    .build();
        } catch (IOException e) {
            log.error("Error was raised when reading {}", searchRequest);
            throw new RuntimeException(e);
        }
    }
}
