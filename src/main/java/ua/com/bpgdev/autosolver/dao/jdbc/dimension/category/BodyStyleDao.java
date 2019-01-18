package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.category.BodyStyle;

public interface BodyStyleDao
        extends DimensionWithCategoryDao<BodyStyle>, PagingAndSortingRepository<BodyStyle, Long> {
}
