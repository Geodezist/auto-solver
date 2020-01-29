package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.category.GearBox;

public interface GearBoxDao
        extends DimensionWithCategoryDao<GearBox>, PagingAndSortingRepository<GearBox, Long> {
}
