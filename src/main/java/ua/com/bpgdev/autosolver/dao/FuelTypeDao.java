package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.FuelType;

public interface FuelTypeDao extends SimpleDimensionDao<FuelType>, PagingAndSortingRepository<FuelType, Long> {
}
