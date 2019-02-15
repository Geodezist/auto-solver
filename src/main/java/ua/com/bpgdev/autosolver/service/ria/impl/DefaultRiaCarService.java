package ua.com.bpgdev.autosolver.service.ria.impl;

import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaCarDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;

import java.io.IOException;
import java.util.List;

@Service
public class DefaultRiaCarService implements RiaCarService {
    private RiaCarDao riaCarDao;

    public DefaultRiaCarService(RiaCarDao riaCarDao) {
        this.riaCarDao = riaCarDao;
    }

    @Override
    public RiaCarDTO getCar(int carId) {
        RiaCarDTO car = null;
        try {
            car = riaCarDao.getCar(carId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    @Override
    public List<RiaCarDTO> getAll(List<Integer> carIds) {
        return riaCarDao.getAll(carIds);
    }
}
