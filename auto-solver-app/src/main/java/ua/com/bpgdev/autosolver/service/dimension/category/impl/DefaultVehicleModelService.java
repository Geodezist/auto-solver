package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleMarkDao;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleModelDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleModelService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultVehicleModelService implements VehicleModelService {

    private static final Sort SORT_BY_NAME_ASC = Sort.by("name").ascending();
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SIMPLE_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

    private final VehicleModelDao vehicleModelDao;
    private final VehicleMarkDao vehicleMarkDao;

    private List<VehicleModel> getAll() {
        List<VehicleModel> vehicleModels = new ArrayList<>();
        vehicleModelDao.findAll(SORT_BY_NAME_ASC).forEach(vehicleModels::add);
        return vehicleModels;
    }

    public int getCount(VehicleMark vehicleMark) {
        return vehicleModelDao.countByVehicleMark(vehicleMark);
    }

    @Override
    @Cacheable(value = "vehicleModelsCache", key = "#root.targetClass + #root.methodName + #categoryValue + #markValue")
    public List<SimpleDTO> getByCategoryValueAndMarkValueDto(int categoryValue, int markValue) {
        Long markId = vehicleMarkDao.findByCategoryValueAndValue(categoryValue, markValue).getId();
        List<SimpleDTO> result = MODEL_MAPPER.map(vehicleModelDao.findByVehicleMarkId(markId, SORT_BY_NAME_ASC), SIMPLE_DTO_TYPE);

        log.debug("Getting all Vehicle Models DTOs. Count of objects - {}", result.size());
        return result;
    }

    @Override
    @CacheEvict(value = "vehicleModelsCache", condition = "#result != 0", allEntries = true)
    public int saveAll(List<VehicleModel> entities) {
        log.debug("Saving all Vehicle Models. Count of incoming objects - {}"
                , entities.size());
        entities.removeAll(getAll());
        log.debug("Saving all Vehicle Models. Count of objects after filtering - {}"
                , entities.size());
        vehicleModelDao.saveAll(entities);
        return entities.size();
    }
}
