package ua.com.bpgdev.autosolver.service.dimension.simple.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.CountryDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.simple.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCountryServiceTest {
    @Mock
    private CountryDao countryDao;
    @InjectMocks
    private DefaultCountryService defaultCountryService;
    private List<Country> countries = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();

    @Before
    public void beforeTest() {
        Country country = new Country();
        country.setId(1L);
        country.setName("Ukraine");
        country.setValue(1);
        countries.add(country);

        SimpleDTO simpleDTO = new SimpleDTO();
        simpleDTO.setName(country.getName());
        simpleDTO.setValue(country.getValue());

        simpleDTOs.add(simpleDTO);
    }

    @Test
    public void getAll() {
        Mockito.when(countryDao.findAll()).thenReturn(countries);
        List<Country> actualCountries = defaultCountryService.getAll();
        assertEquals(countries, actualCountries);
    }

    @Test
    public void getAllDto(){
        Mockito.when(countryDao.findAll()).thenReturn(countries);
        List<SimpleDTO> actualSimpleDTOs = defaultCountryService.getAllDto();
        assertEquals(simpleDTOs, actualSimpleDTOs);
    }

    @Test
    public void saveAll() {
        Mockito.when(countryDao.saveAll(countries)).thenReturn(countries);
        int actualCountryCount = defaultCountryService.saveAll(countries);
        assertEquals(countries.size(), actualCountryCount);
    }
}