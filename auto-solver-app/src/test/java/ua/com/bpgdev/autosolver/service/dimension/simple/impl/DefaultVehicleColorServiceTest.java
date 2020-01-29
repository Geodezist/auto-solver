package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.VehicleColorDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.VehicleColor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultVehicleColorServiceTest {
    @Mock
    private VehicleColorDao vehicleColorDao;
    @InjectMocks
    private DefaultVehicleColorService defaultVehicleColorService;
    private List<VehicleColor> vehicleColors = new ArrayList<>();

    @Before
    public void beforeTest() {
        VehicleColor vehicleColor = new VehicleColor();
        vehicleColor.setId(1L);
        vehicleColor.setName("Red");
        vehicleColor.setValue(1);
        vehicleColors.add(vehicleColor);
    }


    @Test
    public void getAll() {
        Mockito.when(vehicleColorDao.findAll()).thenReturn(vehicleColors);
        List<VehicleColor> actualVehicleColors = defaultVehicleColorService.getAll();
        assertEquals(vehicleColors, actualVehicleColors);
    }

    @Test
    public void saveAll() {
        Mockito.when(vehicleColorDao.saveAll(vehicleColors)).thenReturn(vehicleColors);
        int actualVehicleColorCount = defaultVehicleColorService.saveAll(vehicleColors);
        assertEquals(vehicleColors.size(), actualVehicleColorCount);
    }
}