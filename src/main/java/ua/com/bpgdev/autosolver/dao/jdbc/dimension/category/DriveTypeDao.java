package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.category.DriveType;

public interface DriveTypeDao
        extends DimensionWithCategoryDao<DriveType>, PagingAndSortingRepository<DriveType, Long> {
}
