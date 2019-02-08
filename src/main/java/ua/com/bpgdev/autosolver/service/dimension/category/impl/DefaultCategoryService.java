package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.CategoryDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCategoryService implements CategoryService {
    private final String className = getClass().getSimpleName();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SIMPLE_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

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
    public List<SimpleDTO> getAllDto() {
        List<SimpleDTO> result = MODEL_MAPPER.map(getAll(), SIMPLE_DTO_TYPE);
        logger.debug("Getting all DTOs by {}. Count of oblects - {}"
                , className
                , result.size());
        return result;
    }


    @Override
    public int saveAll(List<Category> categories) {
        logger.debug("Saving all {}. Count of incoming oblects - {}"
                , className
                , categories.size());
        categories.removeAll(getAll());
        logger.debug("Saving all {}. Count of oblects after filtering - {}"
                , className
                , categories.size());
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
