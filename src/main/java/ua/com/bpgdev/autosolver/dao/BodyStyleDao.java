package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.BodyStyle;

public interface BodyStyleDao
        extends DimensionWithCategoryDao<BodyStyle>, PagingAndSortingRepository<BodyStyle, Long> {
}
