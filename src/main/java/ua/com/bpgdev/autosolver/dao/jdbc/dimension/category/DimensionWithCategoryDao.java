package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;

import java.util.List;

@NoRepositoryBean
interface DimensionWithCategoryDao<T> extends Repository<T, Long> {
    List<T> findByCategoryId(Long categoryId);

    List<T> findByCategoryId(Long categoryId, Sort sort);

    List<T> findByCategoryValue(int categoryValue);

    List<T> findByCategoryValue(int categoryValue, Sort sort);

    T findByCategoryIdAndName(Long categoryId, String name);

    T findByCategoryIdAndValue(Long categoryId, int value);

    T findByCategoryAndName(Category category, String name);

    T findByCategoryAndValue(Category category, int value);
}
