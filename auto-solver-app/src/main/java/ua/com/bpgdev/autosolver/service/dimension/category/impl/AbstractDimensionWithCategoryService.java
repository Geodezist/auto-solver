package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.DimensionWithCategory;
import ua.com.bpgdev.autosolver.service.dimension.category.DimensionWithCategoryService;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
public abstract class AbstractDimensionWithCategoryService<T extends DimensionWithCategory>
        implements DimensionWithCategoryService<T, SimpleDTO> {
    private final String className = getClass().getSimpleName();

    static final Sort SORT_BY_NAME_ASC = Sort.by("name").ascending();
    static final Sort SORT_BY_VALUE_ASC = Sort.by("value").ascending();
    static final ModelMapper MODEL_MAPPER = new ModelMapper();
    static final Type CATEGORY_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

    @Override
    @Cacheable(value = "categoryDictionaryCache", key = "#root.targetClass + #root.methodName + #categoryId")
    public List<SimpleDTO> getByCategoryIdDto(Long categoryId) {
        List<SimpleDTO> result = MODEL_MAPPER.map(getByCategoryId(categoryId), CATEGORY_DTO_TYPE);
        log.debug("Getting DTOs by {} filtered by Category id = {}. Count of objects - {}"
                , className
                , categoryId
                , result.size());
        return result;
    }

    @Override
    @Cacheable(value = "categoryDictionaryCache", key = "#root.targetClass + #root.methodName + #categoryValue")
    public List<SimpleDTO> getByCategoryValueDto(int categoryValue) {
        List<SimpleDTO> result = MODEL_MAPPER.map(getByCategoryValue(categoryValue), CATEGORY_DTO_TYPE);
        log.debug("Getting DTOs by {} filtered by Category value = {}. Count of objects - {}"
                , className
                , categoryValue
                , result.size());
        return result;
    }

    void filterEntities(List<T> entities) {
        log.debug("Saving all {}. Count of incoming objects - {}"
                , className
                , entities.size());
        entities.removeAll(getAll());
        log.debug("Saving all {}. Count of objects after filtering - {}"
                , className
                , entities.size());
    }

    @Override
    @CacheEvict(value = "categoryDictionaryCache", condition = "#result != 0", allEntries = true)
    public abstract int saveAll(List<T> entities);
}
