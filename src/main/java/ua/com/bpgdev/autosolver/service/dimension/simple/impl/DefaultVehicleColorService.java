package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.VehicleColorDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.VehicleColor;
import ua.com.bpgdev.autosolver.service.dimension.simple.VehicleColorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultVehicleColorService
        extends AbstractSimpleDimensionService<VehicleColor>
        implements VehicleColorService {
    private VehicleColorDao vehicleColorDao;

    @Autowired
    public DefaultVehicleColorService(VehicleColorDao vehicleColorDao) {
        this.vehicleColorDao = vehicleColorDao;
    }

    @Override
    public List<VehicleColor> getAll() {
        List<VehicleColor> vehicleColors = new ArrayList<>();
        vehicleColorDao.findAll().forEach(vehicleColors::add);
        return vehicleColors;
    }

    @Override
    public int saveAll(List<VehicleColor> entities) {
        filterEntities(entities);
        vehicleColorDao.saveAll(entities);
        return entities.size();
    }
}
