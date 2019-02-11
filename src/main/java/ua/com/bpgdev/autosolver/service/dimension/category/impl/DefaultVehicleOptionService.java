package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleOptionDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleOption;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleOptionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultVehicleOptionService
        extends AbstractDimensionWithCategoryService<VehicleOption>
        implements VehicleOptionService {
    private VehicleOptionDao vehicleOptionDao;

    @Autowired
    public DefaultVehicleOptionService(VehicleOptionDao vehicleOptionDao) {
        this.vehicleOptionDao = vehicleOptionDao;
    }

    @Override
    public List<VehicleOption> getAll() {
        List<VehicleOption> vehicleOptions = new ArrayList<>();
        vehicleOptionDao.findAll().forEach(vehicleOptions::add);
        logger.debug("Getting all VehicleOptions from DAO. Count of oblects - {}"
                , vehicleOptions.size());
        return vehicleOptions;
    }

    @Override
    public List<VehicleOption> getByCategoryId(Long categoryId) {
        List<VehicleOption> result = vehicleOptionDao.findByCategoryId(categoryId);
        logger.debug("Getting VehicleOptions from DAO filtered by Category id = {}. Count of oblects - {}"
                , categoryId
                , result.size());
        return result;
    }

    @Override
    public List<VehicleOption> getByCategoryValue(int categoryValue) {
        List<VehicleOption> result = vehicleOptionDao.findByCategoryValue(categoryValue);
        logger.debug("Getting VehicleOptions from DAO filtered by Category value = {}. Count of oblects - {}"
                , categoryValue
                , result.size());
        return result;
    }

    @Override
    public int saveAll(List<VehicleOption> entities) {
        filterEntities(entities);
        vehicleOptionDao.saveAll(entities);
        return entities.size();
    }
}
