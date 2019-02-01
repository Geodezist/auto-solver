package ua.com.bpgdev.autosolver.service.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    int saveAll(List<Category> categories);

    boolean save(Category category);
}
