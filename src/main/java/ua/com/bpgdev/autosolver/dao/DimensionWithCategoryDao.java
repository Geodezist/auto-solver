package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import ua.com.bpgdev.autosolver.entity.Category;

import java.util.List;

@NoRepositoryBean
interface DimensionWithCategoryDao<T> extends Repository<T, Long> {
    List<T> findByCategoryId(Long categoryId);

    T findByCategoryIdAndName(Long categoryId, String name);

    T findByCategoryIdAndValue(Long categoryId, int value);

    T findByCategoryAndName(Category category, String name);

    T findByCategoryAndValue(Category category, int value);
}
