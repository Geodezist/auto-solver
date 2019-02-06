package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleMarkDao;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleModelDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleModelService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultVehicleModelService
        implements VehicleModelService {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SIMPLE_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

    private VehicleModelDao vehicleModelDao;
    private VehicleMarkDao vehicleMarkDao;

    @Autowired
    public DefaultVehicleModelService(VehicleModelDao vehicleModelDao,
                                      VehicleMarkDao vehicleMarkDao) {
        this.vehicleModelDao = vehicleModelDao;
        this.vehicleMarkDao = vehicleMarkDao;
    }

    private List<VehicleModel> getAll() {
        List<VehicleModel> vehicleModels = new ArrayList<>();
        vehicleModelDao.findAll().forEach(vehicleModels::add);
        return vehicleModels;
    }

    @Override
    public List<SimpleDTO> getByCategoryValueAndMarkValueDto(int categoryValue, int markValue) {
        Long markId = vehicleMarkDao.findByCategoryValueAndValue(categoryValue, markValue).getId();
        return MODEL_MAPPER.map(vehicleModelDao.findByVehicleMarkId(markId), SIMPLE_DTO_TYPE);
    }

    @Override
    public int saveAll(List<VehicleModel> entities) {
        entities.removeAll(getAll());
        vehicleModelDao.saveAll(entities);
        return entities.size();
    }
}
