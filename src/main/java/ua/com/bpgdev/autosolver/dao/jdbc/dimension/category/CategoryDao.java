package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;

public interface CategoryDao extends PagingAndSortingRepository<Category, Long> {
    Category findByValue(int value);

    Category findByName(String name);
}
