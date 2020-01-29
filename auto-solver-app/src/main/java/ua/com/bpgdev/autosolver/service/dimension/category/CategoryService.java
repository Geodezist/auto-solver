package ua.com.bpgdev.autosolver.service.dimension.category;

import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.service.dimension.simple.SimpleDimensionService;

import java.util.List;

public interface CategoryService extends SimpleDimensionService<Category, SimpleDTO> {
    List<Category> getAll();

    int saveAll(List<Category> categories);

    boolean save(Category category);
}
