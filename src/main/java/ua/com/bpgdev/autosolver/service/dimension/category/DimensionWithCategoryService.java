package ua.com.bpgdev.autosolver.service.dimension.category;

import java.util.List;

public interface DimensionWithCategoryService<T> {
    List<T> getAll();

    List<T> getByCategoryId(Long categoryId);

    int saveAll(List<T> entities);
}
