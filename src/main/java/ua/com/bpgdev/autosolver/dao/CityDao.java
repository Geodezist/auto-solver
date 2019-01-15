package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.City;
import ua.com.bpgdev.autosolver.entity.UkraineState;

import java.util.List;

public interface CityDao extends SimpleDimensionDao<City>, PagingAndSortingRepository<City, Long> {
    List<City> findByUkraineStateId(Long ukraineStateId);

    City findByUkraineStateIdAndName(Long ukraineStateId, String name);

    City findByUkraineStateIdAndValue(Long ukraineStateId, int value);

    City findByUkraineStateAndName(UkraineState ukraineState, String name);

    City findByUkraineStateAndValue(UkraineState ukraineState, int value);
}
