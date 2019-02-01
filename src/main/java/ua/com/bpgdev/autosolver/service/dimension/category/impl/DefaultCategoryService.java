package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.CategoryDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCategoryService implements CategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public DefaultCategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        categoryDao.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public int saveAll(List<Category> categories) {
        categories.removeAll(getAll());
        categoryDao.saveAll(categories);
        return categories.size();
    }

    @Override
    public boolean save(Category category) {
        if (getAll().indexOf(category) == -1) {
            categoryDao.save(category);
            return true;
        }
        return false;
    }
}
