package ua.com.bpgdev.autosolver.service.ria;

import java.util.Set;

public interface RiaSearchResultService {
    Set<Integer> getSearchResult(String queryString);
}
