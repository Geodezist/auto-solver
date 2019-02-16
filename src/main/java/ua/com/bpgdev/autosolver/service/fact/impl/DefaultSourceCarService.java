package ua.com.bpgdev.autosolver.service.fact.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.SourceCarDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.service.fact.SourceCarService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultSourceCarService implements SourceCarService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SOURCE_CAR_DTO_TYPE = new TypeToken<List<SourceCar>>() {
    }.getType();

    private SourceCarDao sourceCarDao;

    @Autowired
    public DefaultSourceCarService(SourceCarDao sourceCarDao) {
        this.sourceCarDao = sourceCarDao;
    }

    @Override
    public SourceCar get(Integer sourceCarId) {
        return sourceCarDao.findByCarId(sourceCarId);
    }

    @Override
    public List<Integer> findAllByCarIdIn(List<Integer> sourceCarIds) {
        return sourceCarDao.findAllCarIdByCarIdIn(sourceCarIds);
    }

    @Override
    public List<SourceCar> getAllByIds(List<Integer> sourceCarIds) {
        return sourceCarDao.findAllByCarIdIn(sourceCarIds);
    }

    @Override
    public List<SourceCar> getAll() {
        List<SourceCar> sourceCars = new ArrayList<>();
        sourceCarDao.findAll().forEach(sourceCars::add);
        return sourceCars;
    }

    @Override
    public SourceCar save(SourceCar sourceCar) {
        return sourceCarDao.save(sourceCar);
    }

    public int saveAllDTO(List<RiaCarDTO> riaCarDTOs) {
        List<SourceCar> sourceCars = MODEL_MAPPER.map(riaCarDTOs, SOURCE_CAR_DTO_TYPE);
        return saveAll(sourceCars);
    }

    @Override
    public int saveAll(List<SourceCar> sourceCars) {
        sourceCarDao.saveAll(sourceCars);
        return sourceCars.size();
    }
}
