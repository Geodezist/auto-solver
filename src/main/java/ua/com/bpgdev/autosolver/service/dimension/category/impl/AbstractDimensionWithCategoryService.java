package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.DimensionWithCategory;
import ua.com.bpgdev.autosolver.service.dimension.category.DimensionWithCategoryService;

import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractDimensionWithCategoryService<T extends DimensionWithCategory>
        implements DimensionWithCategoryService<T, SimpleDTO> {
    private final String className = getClass().getSimpleName();
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    static final ModelMapper MODEL_MAPPER = new ModelMapper();
    static final Type CATEGORY_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

    @Override
    public List<SimpleDTO> getByCategoryIdDto(Long categoryId) {
        List<SimpleDTO> result = MODEL_MAPPER.map(getByCategoryId(categoryId), CATEGORY_DTO_TYPE);
        LOGGER.debug("Getting DTOs by {} filtered by Category id = {}. Count of oblects - {}"
                , className
                , categoryId
                , result.size());
        return result;
    }

    @Override
    public List<SimpleDTO> getByCategoryValueDto(int categoryValue) {
        List<SimpleDTO> result = MODEL_MAPPER.map(getByCategoryValue(categoryValue), CATEGORY_DTO_TYPE);
        LOGGER.debug("Getting DTOs by {} filtered by Category value = {}. Count of oblects - {}"
                , className
                , categoryValue
                , result.size());
        return result;
    }

    void filterEntities(List<T> entities){
        LOGGER.debug("Saving all {}. Count of incoming oblects - {}"
                , className
                , entities.size());
        entities.removeAll(getAll());
        LOGGER.debug("Saving all {}. Count of oblects after filtering - {}"
                , className
                , entities.size());
    }
}
