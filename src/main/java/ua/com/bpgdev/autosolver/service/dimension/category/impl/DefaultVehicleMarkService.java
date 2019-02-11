package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleMarkDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleMarkService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultVehicleMarkService
        extends AbstractDimensionWithCategoryService<VehicleMark>
        implements VehicleMarkService {
    private VehicleMarkDao vehicleMarkDao;

    @Autowired
    public DefaultVehicleMarkService(VehicleMarkDao vehicleMarkDao) {
        this.vehicleMarkDao = vehicleMarkDao;
    }

    @Override
    public List<VehicleMark> getAll() {
        List<VehicleMark> vehicleMarks = new ArrayList<>();
        vehicleMarkDao.findAll().forEach(vehicleMarks::add);
        logger.debug("Getting all VehicleMarks from DAO. Count of oblects - {}"
                , vehicleMarks.size());
        return vehicleMarks;
    }

    @Override
    public List<VehicleMark> getByCategoryId(Long categoryId) {
        List<VehicleMark> result = vehicleMarkDao.findByCategoryId(categoryId);
        logger.debug("Getting VehicleMarks from DAO filtered by Category id = {}. Count of oblects - {}"
                , categoryId
                , result.size());
        return result;
    }

    @Override
    public List<VehicleMark> getByCategoryValue(int categoryValue) {
        List<VehicleMark> result = vehicleMarkDao.findByCategoryValue(categoryValue);
        logger.debug("Getting VehicleMarks from DAO filtered by Category value = {}. Count of oblects - {}"
                , categoryValue
                , result.size());
        return result;
    }

    @Override
    @Cacheable(value = "vehicleMarksCache", key = "#root.targetClass + #root.methodName + #categoryValue + #searchString")
    public List<SimpleDTO> getByCategoryValueAndNameStartsWithDto(int categoryValue, String searchString) {
        List<SimpleDTO> result = MODEL_MAPPER
                .map(vehicleMarkDao.findByCategoryValueAndNameStartsWithIgnoreCase(categoryValue, searchString),
                        CATEGORY_DTO_TYPE);
        logger.debug("Getting VehicleMarks from DAO filtered by Category value = {} "
                        + "and Name starts with = {}. Count of oblects - {}"
                , categoryValue
                , searchString
                , result.size());
        return result;
    }

    @Override
    public int saveAll(List<VehicleMark> entities) {
        filterEntities(entities);
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
