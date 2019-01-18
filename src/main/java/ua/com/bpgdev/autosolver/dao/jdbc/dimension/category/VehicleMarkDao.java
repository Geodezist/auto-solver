package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;

public interface VehicleMarkDao
        extends DimensionWithCategoryDao<VehicleMark>, PagingAndSortingRepository<VehicleMark, Long> {
}
