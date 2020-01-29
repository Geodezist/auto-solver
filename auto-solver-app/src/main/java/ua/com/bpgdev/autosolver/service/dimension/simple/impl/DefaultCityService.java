package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        Converter<City, CityDTO> cityCityDTOConverter = DefaultCityService::convert;
        MODEL_MAPPER.addConverter(cityCityDTOConverter);
    }

    static CityDTO convert(MappingContext<City, CityDTO> mappingContext) {
        City city = mappingContext.getSource();
        CityDTO cityDTO = mappingContext.getDestination();
        cityDTO.setUkraineStateName(city.getUkraineState().getName());
        cityDTO.setUkraineStateValue(city.getUkraineState().getValue());

        return cityDTO;
    }

    @Override
    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        cityDao.findAll().forEach(cities::add);
        return cities;
    }

    @Override
    @Cacheable(value = "simpleDictionaryCache", key = "#root.targetClass")
    public List<CityDTO> getAllDto() {
        List<CityDTO> result = MODEL_MAPPER.map(getAll(), CITY_DTO_TYPE);
        logger.debug("Getting all Cities DTOs. Count of objects - {}", result.size());
        return result;
    }

    @Override
    public List<City> getAllByUkraineStateValue(int ukraineStateValue) {
        return cityDao.findByUkraineStateValue(ukraineStateValue);
    }

    public List<SimpleDTO> getAllByUkraineStateValueDto(int ukraineStateValue) {
        List<SimpleDTO> result = MODEL_MAPPER.map(getAllByUkraineStateValue(ukraineStateValue), SIMPLE_DTO_TYPE);
        logger.debug("Getting Cities DTOs by Ukrainian State value = {}. Count of objects - {}"
                , ukraineStateValue
                , result.size());
        return result;
    }

    @Override
    @CacheEvict(value = "simpleDictionaryCache", key = "#root.targetClass", condition = "#result != 0")
    public int saveAll(List<City> entities) {
        logger.debug("Saving all Cities. Count of incoming objects - {}"
                , entities.size());
        entities.removeAll(getAll());
        logger.debug("Saving all Cities. Count of objects after filtering - {}"
                , entities.size());
        cityDao.saveAll(entities);
        return entities.size();
    }
}
