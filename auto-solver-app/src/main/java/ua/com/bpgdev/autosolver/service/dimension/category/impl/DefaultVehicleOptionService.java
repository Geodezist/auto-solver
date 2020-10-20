package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleOptionDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleOption;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleOptionService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultVehicleOptionService
        extends AbstractDimensionWithCategoryService<VehicleOption>
        implements VehicleOptionService {
    private final VehicleOptionDao vehicleOptionDao;

    @Override
    public List<VehicleOption> getAll() {
        List<VehicleOption> vehicleOptions = new ArrayList<>();
        vehicleOptionDao.findAll(SORT_BY_VALUE_ASC).forEach(vehicleOptions::add);
        log.debug("Getting all VehicleOptions from DAO. Count of objects - {}"
                , vehicleOptions.size());
        return vehicleOptions;
    }

    @Override
    public List<VehicleOption> getByCategoryId(Long categoryId) {
        List<VehicleOption> result = vehicleOptionDao.findByCategoryId(categoryId, SORT_BY_VALUE_ASC);
        log.debug("Getting VehicleOptions from DAO filtered by Category id = {}. Count of objects - {}"
                , categoryId
                , result.size());
        return result;
    }

    @Override
    public List<VehicleOption> getByCategoryValue(int categoryValue) {
        List<VehicleOption> result = vehicleOptionDao.findByCategoryValue(categoryValue, SORT_BY_VALUE_ASC);
        log.debug("Getting VehicleOptions from DAO filtered by Category value = {}. Count of objects - {}"
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
