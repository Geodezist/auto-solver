package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleModelDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleModelService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultVehicleModelService implements VehicleModelService {
    private VehicleModelDao vehicleModelDao;

    @Autowired
    public DefaultVehicleModelService(VehicleModelDao vehicleModelDao) {
        this.vehicleModelDao = vehicleModelDao;
    }

    @Override
    public List<VehicleModel> getAll() {
        List<VehicleModel> vehicleModels = new ArrayList<>();
        vehicleModelDao.findAll().forEach(vehicleModels::add);
        return vehicleModels;
    }

    @Override
    public List<VehicleModel> getByVehicleMarkId(Long vehicleMarkId) {
        return vehicleModelDao.findByVehicleMarkId(vehicleMarkId);
    }

    @Override
    public int saveAll(List<VehicleModel> entities) {
        entities.removeAll(getAll());
        vehicleModelDao.saveAll(entities);
        return entities.size();
    }
}
