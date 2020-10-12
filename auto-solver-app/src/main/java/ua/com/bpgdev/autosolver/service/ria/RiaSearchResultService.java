package ua.com.bpgdev.autosolver.service.ria;

import ua.com.bpgdev.autosolver.dto.ria.SearchRequestDTO;

import java.util.Set;

public interface RiaSearchResultService {
    int MAX_TOTAL_COUNT_OF_CAR_IDS = 1000;

    Set<Integer> getSearchResult(SearchRequestDTO searchRequestDTO);
}
