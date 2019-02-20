package ua.com.bpgdev.autosolver.service.fact.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import ua.com.bpgdev.autosolver.dao.jdbc.fact.SourceCarDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSourceCarServiceTest {
    @Mock
    private SourceCarDao sourceCarDao;
    @InjectMocks
    private DefaultSourceCarService defaultSourceCarService;
    private List<SourceCar> expectedSourceCars;
    private SourceCar expectedSourceCar;
    private List<Integer> expectedCarIds;
    private List<Integer> expectedExistingCarIds;
    private List<Integer> expectedNewCarIds;


    @Before
    public void beforeTest() {
        expectedSourceCar = new SourceCar();
        expectedSourceCar.setCarId(1);
        expectedSourceCar.setYear(2000);
        expectedSourceCar.setMileage(10);
        expectedSourceCar.setPriceUsd(10);
        expectedSourceCar.setCategoryValue(1);
        expectedSourceCar.setBodystyleValue(1);
        expectedSourceCar.setMarkValue(1);
        expectedSourceCar.setModelValue(1);
        expectedSourceCar.setDescription("test");
        expectedSourceCar.setFuelTypeName("test");
        expectedSourceCar.setFuelTypeNameEng("test");
        expectedSourceCar.setGearboxName("test");
        expectedSourceCar.setUkraineStateName("test");
        expectedSourceCar.setCityName("test");

        expectedSourceCars = new ArrayList<>();
        expectedSourceCars.add(expectedSourceCar);

        expectedCarIds = new ArrayList<>();
        expectedCarIds.add(1);
        expectedCarIds.add(2);

        expectedExistingCarIds = new ArrayList<>();
        expectedExistingCarIds.add(2);

        expectedNewCarIds = new ArrayList<>();
        expectedNewCarIds.add(1);
    }

    @Test
    public void get() {
        Mockito.when(sourceCarDao.findByCarId(1)).thenReturn(expectedSourceCar);
        SourceCar actualSourceCar = defaultSourceCarService.get(1);
        assertEquals(expectedSourceCar, actualSourceCar);
    }

    @Test
    public void findAllByCarIdIn() {
        Mockito.when(sourceCarDao.findAllCarIdByCarIdIn(expectedCarIds)).thenReturn(expectedExistingCarIds);
        List<Integer> actualExistingCarIds = defaultSourceCarService.findAllByCarIdIn(expectedCarIds);
        assertEquals(expectedExistingCarIds, actualExistingCarIds);
    }

    @Test
    public void getAllByIds() {
        Mockito.when(sourceCarDao.findAllByCarIdIn(expectedNewCarIds)).thenReturn(expectedSourceCars);
        List<SourceCar> actualSourceCars = defaultSourceCarService.getAllByIds(expectedNewCarIds);
        assertEquals(expectedSourceCars, actualSourceCars);
    }

    @Test
    public void getAll() {
        Mockito.when(sourceCarDao.findAll()).thenReturn(expectedSourceCars);
        List<SourceCar> actualSourceCars = defaultSourceCarService.getAll();
        assertEquals(expectedSourceCars, actualSourceCars);
    }

    @Test
    public void save() {
        Mockito.when(sourceCarDao.save(expectedSourceCar)).thenReturn(expectedSourceCar);
        SourceCar actualSourceCar = defaultSourceCarService.save(expectedSourceCar);
        assertEquals(actualSourceCar, expectedSourceCar);
    }

    @Test
    public void saveAllDTO() {
        ModelMapper modelMapper = new ModelMapper();
        Type riaCarDTOType = new TypeToken<List<RiaCarDTO>>() {
        }.getType();
        List<RiaCarDTO> riaCarDTOs = modelMapper.map(expectedSourceCars, riaCarDTOType);
        Mockito.when(sourceCarDao.saveAll(expectedSourceCars)).thenReturn(expectedSourceCars);
        int actualSavedRiaCarDTOs = defaultSourceCarService.saveAllDTO(riaCarDTOs);
        assertEquals(1, actualSavedRiaCarDTOs);
    }

    @Test
    public void saveAll() {
        Mockito.when(sourceCarDao.saveAll(expectedSourceCars)).thenReturn(expectedSourceCars);
        int actualSavedSourceCars = defaultSourceCarService.saveAll(expectedSourceCars);
        assertEquals(1, actualSavedSourceCars);

    }
}