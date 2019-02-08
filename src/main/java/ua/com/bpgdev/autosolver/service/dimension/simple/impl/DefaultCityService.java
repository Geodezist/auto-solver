package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        List<CityDTO> result = MODEL_MAPPER.map(getAll(), CITY_DTO_TYPE);
        logger.debug("Getting all Cities DTOs. Count of oblects - {}", result.size());
        return result;
    }

    @Override
    public List<City> getAllByUkraineStateValue(int ukraneStateValue) {
        return cityDao.findByUkraineStateValue(ukraneStateValue);
    }

    public List<SimpleDTO> getAllByUkraineStateValueDto(int ukraneStateValue) {
        List<SimpleDTO> result = MODEL_MAPPER.map(getAllByUkraineStateValue(ukraneStateValue), SIMPLE_DTO_TYPE);
        logger.debug("Getting Cities DTOs by Ukreainian State value = {}. Count of oblects - {}"
                , ukraneStateValue
                , result.size());
        return result;
    }

    @Override
    public int saveAll(List<City> entities) {
        logger.debug("Saving all Cities. Count of incoming oblects - {}"
                , entities.size());
        entities.removeAll(getAll());
        logger.debug("Saving all Cities. Count of oblects after filtering - {}"
                , entities.size());
        cityDao.saveAll(entities);
        return entities.size();
    }
}
