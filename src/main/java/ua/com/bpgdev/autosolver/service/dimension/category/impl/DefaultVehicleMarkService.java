package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleMarkDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleMarkService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultVehicleMarkService implements VehicleMarkService {
    private VehicleMarkDao vehicleMarkDao;

    @Autowired
    public DefaultVehicleMarkService(VehicleMarkDao vehicleMarkDao) {
        this.vehicleMarkDao = vehicleMarkDao;
    }

    @Override
    public List<VehicleMark> getAll() {
        List<VehicleMark> vehicleMarks = new ArrayList<>();
        vehicleMarkDao.findAll().forEach(vehicleMarks::add);
        return vehicleMarks;
    }

    @Override
    public List<VehicleMark> getByCategoryId(Long categoryId) {
        return vehicleMarkDao.findByCategoryId(categoryId);
    }

    @Override
    public int saveAll(List<VehicleMark> entities) {
        entities.removeAll(getAll());
        vehicleMarkDao.saveAll(entities);
        return entities.size();
    }

    @Override
    public int saveAll(List<VehicleMark> vehicleMarks, Category category) {
        vehicleMarks.removeAll(getByCategoryId(category.getId()));
        vehicleMarkDao.saveAll(vehicleMarks);
        return vehicleMarks.size();
    }
}
