package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.VehicleColorDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.VehicleColor;
import ua.com.bpgdev.autosolver.service.dimension.simple.VehicleColorService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultVehicleColorService
        extends AbstractSimpleDimensionService<VehicleColor>
        implements VehicleColorService {
    private final VehicleColorDao vehicleColorDao;

    @Override
    public List<VehicleColor> getAll() {
        List<VehicleColor> vehicleColors = new ArrayList<>();
        vehicleColorDao.findAll().forEach(vehicleColors::add);
        logger.debug("Getting all VehicleColor from DAO. Count of objects - {}"
                , vehicleColors.size());
        return vehicleColors;
    }

    @Override
    public int saveAll(List<VehicleColor> entities) {
        filterEntities(entities);
        vehicleColorDao.saveAll(entities);
        return entities.size();
    }
}
