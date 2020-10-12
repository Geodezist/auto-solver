package ua.com.bpgdev.autosolver.populator.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.populator.dimension.SimpleCommonPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.category.CategoryPopulator;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

@Component
public class DefaultCategoryPopulator extends SimpleCommonPopulator<Category> implements CategoryPopulator {
    private static final String CATEGORY_API_ENTITY = "categories/";
    private final CategoryService categoryService;

    @Autowired
    public DefaultCategoryPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                    CategoryService categoryService,
                                    RestTemplate restTemplate) {
        super(restApiUrlBuilder, restTemplate);
        this.categoryService = categoryService;
    }

    @Override
    public int populateAll() {
        return categoryService.saveAll(getUpstreamData(Category[].class, CATEGORY_API_ENTITY));
    }
}
