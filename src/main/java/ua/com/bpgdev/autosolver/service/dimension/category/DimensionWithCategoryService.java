package ua.com.bpgdev.autosolver.service.dimension.category;

import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.DimensionWithCategory;

import java.util.List;

public interface DimensionWithCategoryService<T extends DimensionWithCategory, D extends SimpleDTO> {
    List<T> getAll();

    List<T> getByCategoryId(Long categoryId);

    List<T> getByCategoryValue(int categoryValue);

    List<D> getByCategoryIdDto(Long categoryId);

    List<D> getByCategoryValueDto(int categoryValue);

    int saveAll(List<T> entities);
}
