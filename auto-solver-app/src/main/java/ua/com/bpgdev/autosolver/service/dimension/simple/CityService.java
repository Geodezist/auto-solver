package ua.com.bpgdev.autosolver.service.dimension.simple;

import ua.com.bpgdev.autosolver.dto.dimension.simple.CityDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.simple.City;

import java.util.List;

public interface CityService extends SimpleDimensionService<City, CityDTO> {
    List<City> getAllByUkraineStateValue(int ukraineStateValue);

    List<SimpleDTO> getAllByUkraineStateValueDto(int ukraineStateValue);
}
