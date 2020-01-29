package ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.simple.City;

import java.util.List;

public interface CityDao extends SimpleDimensionDao<City>, PagingAndSortingRepository<City, Long> {
    List<City> findByUkraineStateValue(int ukraineStateValue);

    City findByUkraineStateIdAndValue(Long ukraineStateId, int value);
}
