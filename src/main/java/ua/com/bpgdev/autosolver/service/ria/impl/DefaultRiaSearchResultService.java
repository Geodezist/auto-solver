package ua.com.bpgdev.autosolver.service.ria.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaSearchResultDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaSearchResultDTO;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;

import java.util.HashSet;
import java.util.Set;

@Service
public class DefaultRiaSearchResultService implements RiaSearchResultService {
    private static final int MAX_CARIDS_IN_RESPONSE = 10;
    private RiaSearchResultDao riaSearchResultDao;

    @Autowired
    public DefaultRiaSearchResultService(RiaSearchResultDao riaSearchResultDao) {
        this.riaSearchResultDao = riaSearchResultDao;
    }

    @Override
    public Set<Integer> getSearchResult(String queryString) {
        RiaSearchResultDTO riaSearchResultDTO = riaSearchResultDao.getSearchResult(queryString);
        int totalCountOfCarIds = riaSearchResultDTO.getTotalCount();
        Set<Integer> carIds = new HashSet<>(riaSearchResultDTO.getCarIds());

        for (int page = 1; page * MAX_CARIDS_IN_RESPONSE < totalCountOfCarIds; page++) {
            RiaSearchResultDTO nextRiaSearchResultDTO = riaSearchResultDao.getSearchResult(queryString + "&page=" + page);
            carIds.addAll(nextRiaSearchResultDTO.getCarIds());
        }

        return carIds;
    }
}
