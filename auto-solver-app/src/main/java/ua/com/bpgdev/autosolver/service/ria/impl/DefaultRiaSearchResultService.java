package ua.com.bpgdev.autosolver.service.ria.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaSearchResultDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaSearchResultDTO;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;

import java.util.HashSet;
import java.util.Set;

@Service
public class DefaultRiaSearchResultService implements RiaSearchResultService {
    private static final int MAX_TOTAL_COUNT_OF_CAR_IDS = 1000;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RiaSearchResultDao riaSearchResultDao;

    @Autowired
    public DefaultRiaSearchResultService(RiaSearchResultDao riaSearchResultDao) {
        this.riaSearchResultDao = riaSearchResultDao;
    }

    @Override
    public Set<Integer> getSearchResult(String queryString) {
        RiaSearchResultDTO riaSearchResultDTO = riaSearchResultDao.getSearchResult(queryString);
        int totalCountOfCarIds = riaSearchResultDTO.getTotalCount();

        int pageSize = riaSearchResultDTO.getPageSize();
        Set<Integer> carIds = new HashSet<>(riaSearchResultDTO.getCarIds());

        if (totalCountOfCarIds <= MAX_TOTAL_COUNT_OF_CAR_IDS) {
            for (int page = 1; page * pageSize < totalCountOfCarIds; page++) {
                RiaSearchResultDTO nextRiaSearchResultDTO = riaSearchResultDao.getSearchResult(queryString + "&page=" + page);
                carIds.addAll(nextRiaSearchResultDTO.getCarIds());
            }
        } else {
            logger.info("Too many car ids returned - {}, returned first {} carIds!", totalCountOfCarIds, pageSize);
        }
        logger.debug("Got {} car ids from {}", carIds.size(), queryString);
        return carIds;
    }
}
