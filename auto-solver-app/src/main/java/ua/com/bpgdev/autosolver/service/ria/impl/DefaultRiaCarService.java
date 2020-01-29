package ua.com.bpgdev.autosolver.service.ria.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaCarDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;
import ua.com.bpgdev.autosolver.util.ProgressStatus;

import java.util.List;

@Service
public class DefaultRiaCarService implements RiaCarService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RiaCarDao riaCarDao;

    public DefaultRiaCarService(RiaCarDao riaCarDao) {
        this.riaCarDao = riaCarDao;
    }

    @Override
    public RiaCarDTO getCar(Integer carId) {
        RiaCarDTO car = null;
        try {
            car = riaCarDao.getCar(carId);
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted!", e);
            Thread.currentThread().interrupt();
        }
        return car;
    }

    @Override
    public List<RiaCarDTO> getAll(List<Integer> carIds, ProgressStatus progressStatus) {
        return riaCarDao.getAll(carIds, progressStatus);
    }
}
