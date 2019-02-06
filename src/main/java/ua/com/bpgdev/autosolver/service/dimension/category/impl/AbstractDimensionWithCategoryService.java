package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.DimensionWithCategory;
import ua.com.bpgdev.autosolver.service.dimension.category.DimensionWithCategoryService;

import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractDimensionWithCategoryService<T extends DimensionWithCategory>
        implements DimensionWithCategoryService<T, SimpleDTO> {
    static final ModelMapper MODEL_MAPPER = new ModelMapper();
    static final Type CATEGORY_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

    @Override
    public List<SimpleDTO> getByCategoryIdDto(Long categoryId) {
        return MODEL_MAPPER.map(getByCategoryId(categoryId), CATEGORY_DTO_TYPE);
    }

    @Override
    public List<SimpleDTO> getByCategoryValueDto(int categoryValue) {
        return MODEL_MAPPER.map(getByCategoryValue(categoryValue), CATEGORY_DTO_TYPE);
    }
}
