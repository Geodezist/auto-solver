package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.Category;

public interface CategoryDao extends PagingAndSortingRepository<Category, Long> {
    Category findByValue(int value);

    Category findByName(String name);
}
