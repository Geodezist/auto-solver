package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.spi.MappingContext;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.CityDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.CityDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.simple.City;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCityServiceTest {
    @Mock
    private CityDao cityDao;
    @Mock
    MappingContext<City, CityDTO> mappingContext;
    @InjectMocks
    private DefaultCityService defaultCityService;
    private List<City> cities = new ArrayList<>();
    private List<CityDTO> cityDTOs = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();
    private City city = new City();
    private CityDTO cityDTO = new CityDTO();

    @Before
    public void beforeTest(){
        UkraineState ukraineState = new UkraineState("Kyiv", 100);

        city.setId(1L);
        city.setUkraineState(ukraineState);
        city.setName("Kyiv");
        city.setValue(1);

        cities.add(city);

        cityDTO.setUkraineStateName(ukraineState.getName());
        cityDTO.setUkraineStateValue(ukraineState.getValue());
        cityDTO.setName(city.getName());
        cityDTO.setValue(city.getValue());

        cityDTOs.add(cityDTO);

        SimpleDTO simpleDTO = new SimpleDTO();
        simpleDTO.setName(city.getName());
        simpleDTO.setValue(city.getValue());

        simpleDTOs.add(simpleDTO);
    }

    @Test
    public void getAll() {
        Mockito.when(cityDao.findAll()).thenReturn(cities);
        List<City> actualCities = defaultCityService.getAll();
        assertEquals(cities, actualCities);
    }

    @Test
    public void getAllDto() {
        Mockito.when(cityDao.findAll()).thenReturn(cities);
        List<CityDTO> actualCityDTOs = defaultCityService.getAllDto();
        assertEquals(cityDTOs, actualCityDTOs);
    }

    @Test
    public void getAllByUkraineStateValue() {
        Mockito.when(cityDao.findByUkraineStateValue(100)).thenReturn(cities);
        List<City> actualCities = defaultCityService.getAllByUkraineStateValue(100);
        assertEquals(cities, actualCities);
    }

    @Test
    public void getAllByUkraineStateValueDto() {
        Mockito.when(cityDao.findByUkraineStateValue(100)).thenReturn(cities);
        List<SimpleDTO> actualCityDTOs = defaultCityService.getAllByUkraineStateValueDto(100);
        assertEquals(simpleDTOs, actualCityDTOs);
    }

    @Test
    public void saveAll() {
        Mockito.when(cityDao.saveAll(cities)).thenReturn(cities);
        int actualCityCount = defaultCityService.saveAll(cities);
        assertEquals(cities.size(), actualCityCount);
    }

    @Test
    public void convert() {
        Mockito.when(mappingContext.getSource()).thenReturn(city);
        Mockito.when(mappingContext.getDestination()).thenReturn(cityDTO);
        CityDTO actualCityDTO = DefaultCityService.convert(mappingContext);
        assertEquals(cityDTO, actualCityDTO);
    }
}