package ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.simple.VehicleColor;

public interface VehicleColorDao extends SimpleDimensionDao<VehicleColor>, PagingAndSortingRepository<VehicleColor, Long> {
}
