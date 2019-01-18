package ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.simple.City;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;

import java.util.List;

public interface CityDao extends SimpleDimensionDao<City>, PagingAndSortingRepository<City, Long> {
    List<City> findByUkraineStateId(Long ukraineStateId);

    City findByUkraineStateIdAndName(Long ukraineStateId, String name);

    City findByUkraineStateIdAndValue(Long ukraineStateId, int value);

    City findByUkraineStateAndName(UkraineState ukraineState, String name);

    City findByUkraineStateAndValue(UkraineState ukraineState, int value);
}
