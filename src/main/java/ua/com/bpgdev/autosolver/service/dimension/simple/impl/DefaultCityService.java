package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.CityDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.CityDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.simple.City;
import ua.com.bpgdev.autosolver.service.dimension.simple.CityService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCityService implements CityService {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SIMPLE_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();
    private static final Type CITY_DTO_TYPE = new TypeToken<List<CityDTO>>() {
    }.getType();


    private CityDao cityDao;

    @Autowired
    public DefaultCityService(CityDao cityDao) {
        this.cityDao = cityDao;
        Converter<City, CityDTO> cityCityDTOConverter = mappingContext -> {
            City city = mappingContext.getSource();
            CityDTO cityDTO = mappingContext.getDestination();
            cityDTO.setUkraineStateName(city.getUkraineState().getName());
            cityDTO.setUkraineStateValue(city.getUkraineState().getValue());

            return cityDTO;
        };
        MODEL_MAPPER.addConverter(cityCityDTOConverter);
    }

    @Override
    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        cityDao.findAll().forEach(cities::add);
        return cities;
    }

    @Override
    public List<CityDTO> getAllDto() {
        return MODEL_MAPPER.map(getAll(), CITY_DTO_TYPE);
    }

    @Override
    public List<City> getAllByUkraineStateValue(int ukraneStateValue) {
        return cityDao.findByUkraineStateValue(ukraneStateValue);
    }

    public List<SimpleDTO> getAllByUkraineStateValueDto(int ukraneStateValue) {
        return MODEL_MAPPER.map(getAllByUkraineStateValue(ukraneStateValue), SIMPLE_DTO_TYPE);
    }

    @Override
    public int saveAll(List<City> entities) {
        entities.removeAll(getAll());
        cityDao.saveAll(entities);
        return entities.size();
    }
}
