package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.DriveTypeDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.DriveType;
import ua.com.bpgdev.autosolver.service.dimension.category.DriveTypeService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultDriveTypeService
        extends AbstractDimensionWithCategoryService<DriveType>
        implements DriveTypeService {
    private final DriveTypeDao driveTypeDao;

    @Override
    public List<DriveType> getAll() {
        List<DriveType> driveTypes = new ArrayList<>();
        driveTypeDao.findAll(SORT_BY_VALUE_ASC).forEach(driveTypes::add);
        log.debug("Getting all DriveTypes from DAO. Count of objects - {}"
                , driveTypes.size());
        return driveTypes;
    }

    @Override
    public List<DriveType> getByCategoryId(Long categoryId) {
        List<DriveType> result = driveTypeDao.findByCategoryId(categoryId, SORT_BY_VALUE_ASC);
        log.debug("Getting DriveTypes from DAO filtered by Category id = {}. Count of objects - {}"
                , categoryId
                , result.size());
        return result;
    }

    @Override
    public List<DriveType> getByCategoryValue(int categoryValue) {
        List<DriveType> result = driveTypeDao.findByCategoryValue(categoryValue, SORT_BY_VALUE_ASC);
        log.debug("Getting DriveTypes from DAO filtered by Category value = {}. Count of objects - {}"
                , categoryValue
                , result.size());
        return result;
    }

    @Override
    public int saveAll(List<DriveType> entities) {
        filterEntities(entities);
        driveTypeDao.saveAll(entities);
        return entities.size();
    }
}
