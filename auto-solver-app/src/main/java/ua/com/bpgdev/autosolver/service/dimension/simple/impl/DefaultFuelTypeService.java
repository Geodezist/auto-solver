package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.FuelTypeDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.FuelType;
import ua.com.bpgdev.autosolver.service.dimension.simple.FuelTypeService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultFuelTypeService
        extends AbstractSimpleDimensionService<FuelType>
        implements FuelTypeService {

    private final FuelTypeDao fuelTypeDao;

    @Override
    public List<FuelType> getAll() {
        List<FuelType> fuelTypes = new ArrayList<>();
        fuelTypeDao.findAll().forEach(fuelTypes::add);
        logger.debug("Getting all FuelTypes from DAO. Count of objects - {}"
                , fuelTypes.size());
        return fuelTypes;
    }

    @Override
    public int saveAll(List<FuelType> entities) {
        filterEntities(entities);
        fuelTypeDao.saveAll(entities);
        return entities.size();
    }
}
