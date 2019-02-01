package ua.com.bpgdev.autosolver.populator.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.DriveType;
import ua.com.bpgdev.autosolver.populator.dimension.category.DriveTypePopulator;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.service.dimension.category.DriveTypeService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.List;

@Component
public class DefaultDriveTypePopulator extends CategoryCommonPopulator<DriveType> implements DriveTypePopulator {
    private static final String DRIVE_TYPE_API_ENTITY = "driverTypes";
    private DriveTypeService driveTypeService;

    @Autowired
    public DefaultDriveTypePopulator(RestApiUrlBuilder restApiUrlBuilder,
                                     CategoryService categoryService,
                                     DriveTypeService driveTypeService) {
        super(restApiUrlBuilder, categoryService);
        this.driveTypeService = driveTypeService;
    }

    @Override
    public int populateAll() {
        List<Category> categories = categoryService.getAll();
        int result = 0;
        for (Category category : categories) {
            List<DriveType> driveTypes = getUpstreamData(DriveType[].class, category, DRIVE_TYPE_API_ENTITY);
            driveTypes.forEach(driveType -> driveType.setCategory(category));
            result += driveTypeService.saveAll(driveTypes);
        }
        return result;
    }

    @Override
    public boolean populateDriveType(DriveType driveType) {
        return false;
    }
}
