package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.GearBox;

public interface GearBoxDao
        extends DimensionWithCategoryDao<GearBox>, PagingAndSortingRepository<GearBox, Long> {
}
