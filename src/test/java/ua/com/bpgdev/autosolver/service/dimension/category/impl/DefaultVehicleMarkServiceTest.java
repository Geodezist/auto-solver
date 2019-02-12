package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleMarkDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultVehicleMarkServiceTest {
    @Mock
    private VehicleMarkDao vehicleMarkDao;
    @InjectMocks
    private DefaultVehicleMarkService defaultVehicleMarkService;
    private List<VehicleMark> driveTypes = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();
    private VehicleMark vehicleMark = new VehicleMark();
    private SimpleDTO simpleDTO = new SimpleDTO();


    @Before
    public void beforeTest(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Lorry");
        category.setValue(1);

        vehicleMark.setId(1L);
        vehicleMark.setCategory(category);
        vehicleMark.setName("Sedan");
        vehicleMark.setValue(1);
        driveTypes.add(vehicleMark);

        simpleDTO.setName(vehicleMark.getName());
        simpleDTO.setValue(vehicleMark.getValue());
        simpleDTOs.add(simpleDTO);
    }

    @Test
    public void getAll() {
        Mockito.when(vehicleMarkDao.findAll()).thenReturn(driveTypes);
        List<VehicleMark> actualVehicleMarks = defaultVehicleMarkService.getAll();
        assertEquals(driveTypes, actualVehicleMarks);
    }

    @Test
    public void getByCategoryId() {
        Mockito.when(vehicleMarkDao.findByCategoryId(1L)).thenReturn(driveTypes);
        List<VehicleMark> actulaVehicleMarks = defaultVehicleMarkService.getByCategoryId(1L);
        assertEquals(driveTypes, actulaVehicleMarks);
    }

    @Test
    public void getByCategoryValue() {
        Mockito.when(vehicleMarkDao.findByCategoryValue(1)).thenReturn(driveTypes);
        List<VehicleMark> actulaVehicleMarks = defaultVehicleMarkService.getByCategoryValue(1);
        assertEquals(driveTypes, actulaVehicleMarks);
    }

    @Test
    public void saveAll() {
        Mockito.when(vehicleMarkDao.saveAll(driveTypes)).thenReturn(driveTypes);
        int actualVehicleMarkCount = defaultVehicleMarkService.saveAll(driveTypes);
        assertEquals(driveTypes.size(), actualVehicleMarkCount);
    }
}