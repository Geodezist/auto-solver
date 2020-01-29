package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.FuelTypeDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.FuelType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFuelTypeServiceTest {
    @Mock
    private FuelTypeDao fuelTypeDao;
    @InjectMocks
    private DefaultFuelTypeService defaultFuelTypeService;
    private List<FuelType> fuelTypes = new ArrayList<>();

    @Before
    public void beforeTest(){
        FuelType fuelType = new FuelType();
        fuelType.setId(1L);
        fuelType.setName("Gas");
        fuelType.setValue(1);
        fuelTypes.add(fuelType);
    }

    @Test
    public void getAll() {
        Mockito.when(fuelTypeDao.findAll()).thenReturn(fuelTypes);
        List<FuelType> actualFuelTypes = defaultFuelTypeService.getAll();
        assertEquals(fuelTypes, actualFuelTypes);
    }

    @Test
    public void saveAll() {
        Mockito.when(fuelTypeDao.saveAll(fuelTypes)).thenReturn(fuelTypes);
        int actualFuelTypeCount = defaultFuelTypeService.saveAll(fuelTypes);
        assertEquals(fuelTypes.size(), actualFuelTypeCount);
    }
}