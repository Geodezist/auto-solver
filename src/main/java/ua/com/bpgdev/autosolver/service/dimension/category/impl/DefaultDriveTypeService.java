package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.DriveTypeDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.DriveType;
import ua.com.bpgdev.autosolver.service.dimension.category.DriveTypeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDriveTypeService
        extends AbstractDimensionWithCategoryService<DriveType>
        implements DriveTypeService {
    private DriveTypeDao driveTypeDao;

    @Autowired
    public DefaultDriveTypeService(DriveTypeDao driveTypeDao) {
        this.driveTypeDao = driveTypeDao;
    }

    @Override
    public List<DriveType> getAll() {
        List<DriveType> driveTypes = new ArrayList<>();
        driveTypeDao.findAll().forEach(driveTypes::add);
        return driveTypes;
    }

    @Override
    public List<DriveType> getByCategoryId(Long categoryId) {
        return driveTypeDao.findByCategoryId(categoryId);
    }

    @Override
    public List<DriveType> getByCategoryValue(int categoryValue) {
        return driveTypeDao.findByCategoryValue(categoryValue);
    }

    @Override
    public int saveAll(List<DriveType> entities) {
        entities.removeAll(getAll());
        driveTypeDao.saveAll(entities);
        return entities.size();
    }
}
