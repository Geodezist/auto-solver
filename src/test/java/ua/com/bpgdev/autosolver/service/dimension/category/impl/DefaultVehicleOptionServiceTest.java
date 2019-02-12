package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleOptionDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleOption;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultVehicleOptionServiceTest {
    @Mock
    private VehicleOptionDao vehicleOptionDao;
    @InjectMocks
    private DefaultVehicleOptionService defaultVehicleOptionService;
    private List<VehicleOption> vehicleOptions = new ArrayList<>();
    private VehicleOption vehicleOption = new VehicleOption();

    @Before
    public void beforeTest() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Lorry");
        category.setValue(1);

        vehicleOption.setId(1L);
        vehicleOption.setCategory(category);
        vehicleOption.setName("Sedan");
        vehicleOption.setValue(1);
        vehicleOptions.add(vehicleOption);
    }

    @Test
    public void getAll() {
        Mockito.when(vehicleOptionDao.findAll()).thenReturn(vehicleOptions);
        List<VehicleOption> actualVehicleOptions = defaultVehicleOptionService.getAll();
        assertEquals(vehicleOptions, actualVehicleOptions);
    }

    @Test
    public void getByCategoryId() {
        Mockito.when(vehicleOptionDao.findByCategoryId(1L)).thenReturn(vehicleOptions);
        List<VehicleOption> actulaVehicleOptions = defaultVehicleOptionService.getByCategoryId(1L);
        assertEquals(vehicleOptions, actulaVehicleOptions);
    }

    @Test
    public void getByCategoryValue() {
        Mockito.when(vehicleOptionDao.findByCategoryValue(1)).thenReturn(vehicleOptions);
        List<VehicleOption> actulaVehicleOptions = defaultVehicleOptionService.getByCategoryValue(1);
        assertEquals(vehicleOptions, actulaVehicleOptions);
    }

    @Test
    public void saveAll() {
        Mockito.when(vehicleOptionDao.saveAll(vehicleOptions)).thenReturn(vehicleOptions);
        int actualVehicleOptionCount = defaultVehicleOptionService.saveAll(vehicleOptions);
        assertEquals(vehicleOptions.size(), actualVehicleOptionCount);
    }
}