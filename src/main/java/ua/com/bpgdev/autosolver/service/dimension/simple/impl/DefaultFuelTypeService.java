package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.FuelTypeDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.FuelType;
import ua.com.bpgdev.autosolver.service.dimension.simple.FuelTypeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultFuelTypeService implements FuelTypeService {
    private FuelTypeDao fuelTypeDao;

    @Autowired
    public DefaultFuelTypeService(FuelTypeDao fuelTypeDao) {
        this.fuelTypeDao = fuelTypeDao;
    }

    @Override
    public List<FuelType> getAll() {
        List<FuelType> fuelTypes = new ArrayList<>();
        fuelTypeDao.findAll().forEach(fuelTypes::add);
        return fuelTypes;
    }

    @Override
    public int saveAll(List<FuelType> entities) {
        entities.removeAll(getAll());
        fuelTypeDao.saveAll(entities);
        return entities.size();
    }

    @Override
    public boolean save(FuelType entity) {
        if (getAll().indexOf(entity) == -1) {
            fuelTypeDao.save(entity);
            return true;
        }
        return false;
    }
}
