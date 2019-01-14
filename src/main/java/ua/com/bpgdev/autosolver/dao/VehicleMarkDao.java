package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.VehicleMark;

public interface VehicleMarkDao
        extends DimensionWithCategoryDao<VehicleMark>, PagingAndSortingRepository<VehicleMark, Long> {
}
