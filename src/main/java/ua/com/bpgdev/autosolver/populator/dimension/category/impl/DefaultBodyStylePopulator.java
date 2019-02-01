package ua.com.bpgdev.autosolver.populator.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.dimension.category.BodyStyle;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.populator.dimension.category.BodyStylePopulator;
import ua.com.bpgdev.autosolver.service.dimension.category.BodyStyleService;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.List;

@Component
public class DefaultBodyStylePopulator extends CategoryCommonPopulator<BodyStyle> implements BodyStylePopulator {
    private static final String BODY_STYLE_API_ENTITY = "bodystyles";
    private BodyStyleService bodyStyleService;

    @Autowired
    public DefaultBodyStylePopulator(RestApiUrlBuilder restApiUrlBuilder,
                                     CategoryService categoryService,
                                     BodyStyleService bodyStyleService) {
        super(restApiUrlBuilder, categoryService);
        this.bodyStyleService = bodyStyleService;
    }

    @Override
    public int populateAll() {
        List<Category> categories = categoryService.getAll();
        int result = 0;
        for (Category category : categories) {
            List<BodyStyle> bodyStyles = getUpstreamData(BodyStyle[].class, category, BODY_STYLE_API_ENTITY);
            bodyStyles.forEach(bodyStyle -> bodyStyle.setCategory(category));
            result += bodyStyleService.saveAll(bodyStyles);
        }
        return result;
    }

    @Override
    public boolean populateBodyStyle(BodyStyle bodyStyle) {
        return false;
    }
}
