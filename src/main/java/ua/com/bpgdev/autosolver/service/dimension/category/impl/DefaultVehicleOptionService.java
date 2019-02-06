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
        return vehicleOptions;
    }

    @Override
    public List<VehicleOption> getByCategoryId(Long categoryId) {
        return vehicleOptionDao.findByCategoryId(categoryId);
    }

    @Override
    public List<VehicleOption> getByCategoryValue(int categoryValue) {
        return vehicleOptionDao.findByCategoryValue(categoryValue);
    }

    @Override
    public int saveAll(List<VehicleOption> entities) {
        entities.removeAll(getAll());
        vehicleOptionDao.saveAll(entities);
        return entities.size();
    }
}
