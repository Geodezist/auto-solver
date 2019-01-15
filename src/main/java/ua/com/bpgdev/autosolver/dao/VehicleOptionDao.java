package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.VehicleOption;

public interface VehicleOptionDao
        extends DimensionWithCategoryDao<VehicleOption>, PagingAndSortingRepository<VehicleOption, Long> {
}
