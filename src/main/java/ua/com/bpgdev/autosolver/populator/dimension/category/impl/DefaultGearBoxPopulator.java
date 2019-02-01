package ua.com.bpgdev.autosolver.populator.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.GearBox;
import ua.com.bpgdev.autosolver.populator.dimension.category.GearBoxPopulator;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.service.dimension.category.GearBoxService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.List;

@Component
public class DefaultGearBoxPopulator extends CategoryCommonPopulator<GearBox> implements GearBoxPopulator {
    private static final String GEARBOX_API_ENTITY = "gearboxes";
    private GearBoxService gearBoxService;

    @Autowired
    public DefaultGearBoxPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                   CategoryService categoryService,
                                   GearBoxService gearBoxService) {
        super(restApiUrlBuilder, categoryService);
        this.gearBoxService = gearBoxService;
    }

    @Override
    public int populateAll() {
        List<Category> categories = categoryService.getAll();
        int result = 0;
        for (Category category : categories) {
            List<GearBox> driveTypes = getUpstreamData(GearBox[].class, category, GEARBOX_API_ENTITY);
            driveTypes.forEach(driveType -> driveType.setCategory(category));
            result += gearBoxService.saveAll(driveTypes);
        }
        return result;
    }

    @Override
    public boolean populateGearBox(GearBox gearBox) {
        return false;
    }
}
