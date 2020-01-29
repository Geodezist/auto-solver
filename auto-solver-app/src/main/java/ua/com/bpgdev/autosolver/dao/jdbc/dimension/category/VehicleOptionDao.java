package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleOption;

public interface VehicleOptionDao
        extends DimensionWithCategoryDao<VehicleOption>, PagingAndSortingRepository<VehicleOption, Long> {
}
