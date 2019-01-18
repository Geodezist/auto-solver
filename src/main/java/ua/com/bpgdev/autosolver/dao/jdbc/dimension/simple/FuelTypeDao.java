package ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.simple.FuelType;

public interface FuelTypeDao extends SimpleDimensionDao<FuelType>, PagingAndSortingRepository<FuelType, Long> {
}
