package ua.com.bpgdev.autosolver.populator.dimension.simple.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.com.bpgdev.autosolver.entity.dimension.simple.Country;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCountryPopulatorTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private RestApiUrlBuilder restApiUrlBuilder;
    @InjectMocks
    private DefaultCountryPopulator defaultCountryPopulator;
    private Country[] countries = new Country[1];
    private Country country = new Country();
    private Class<Country[]> countryClass = Country[].class;

    @Before
    public void beforeTest() {
        country.setId(1L);
        country.setName("Ukraine");
        country.setValue(1);
        countries[0] = country;
    }

    @Test
    public void populateAll() {
    }

    @Test
    public void getUpstreamData() {

        ResponseEntity<Country[]> responseEntity = new ResponseEntity<>(countries, HttpStatus.OK);
        ResponseEntity<Country[]> emptyResponseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        String urlReturnData = "https://test.bpgdev.com.ua/";
        String urlDoesNotReturnData = "https://test.bpgdev.com.ua/NA";
        String dataIsPresent = "Data is present";
        String dataIsNotPresent = "Data is not present";

        Mockito.when(restApiUrlBuilder.getUrlApi(dataIsPresent)).thenReturn(urlReturnData);
        Mockito.when(restApiUrlBuilder.getUrlApi(dataIsNotPresent)).thenReturn(urlDoesNotReturnData);
        Mockito.when(restTemplate.getForEntity(urlReturnData, countryClass)).thenReturn(responseEntity);
        Mockito.when(restTemplate.getForEntity(urlDoesNotReturnData, countryClass)).thenReturn(emptyResponseEntity);

        List<Country> actualCountries = defaultCountryPopulator.getUpstreamData(countryClass, dataIsPresent);
        assertEquals(Arrays.asList(countries), actualCountries);

        List<Country> actualEmptyCountries = defaultCountryPopulator.getUpstreamData(countryClass, dataIsNotPresent);
        assertEquals(new ArrayList<Country>(), actualEmptyCountries);
    }
}