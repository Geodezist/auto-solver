package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.VehicleColor;

public interface VehicleColorDao extends SimpleDimensionDao<VehicleColor>, PagingAndSortingRepository<VehicleColor, Long> {
}
