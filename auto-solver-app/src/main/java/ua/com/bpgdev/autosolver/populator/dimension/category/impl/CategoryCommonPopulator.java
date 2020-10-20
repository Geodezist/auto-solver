package ua.com.bpgdev.autosolver.populator.dimension.category.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class CategoryCommonPopulator<T> {
    private static final String CATEGORY_API_ENTITY = "categories";
    private final RestApiUrlBuilder restApiUrlBuilder;
    CategoryService categoryService;

    CategoryCommonPopulator(RestApiUrlBuilder restApiUrlBuilder, CategoryService categoryService) {
        this.restApiUrlBuilder = restApiUrlBuilder;
        this.categoryService = categoryService;
    }

    List<T> getUpstreamData(Class<T[]> objectClass,
                            Category category,
                            String apiEntityName) {
        List<T> result = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<T[]> responseEntity =
                restTemplate.getForEntity(
                        restApiUrlBuilder
                                .getUrlApi(CATEGORY_API_ENTITY
                                        + "/"
                                        + category.getValue()
                                        + "/"
                                        + apiEntityName),
                        objectClass);
        if (responseEntity.getBody() != null) {
            result = new ArrayList<>(Arrays.asList(responseEntity.getBody()));
        }
        return result;
    }
}
