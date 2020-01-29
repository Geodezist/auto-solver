package ua.com.bpgdev.autosolver.dao.rest.ria;

import ua.com.bpgdev.autosolver.dto.ria.RiaSearchResultDTO;

public interface RiaSearchResultDao {
    RiaSearchResultDTO getSearchResult(String queryString);
}
