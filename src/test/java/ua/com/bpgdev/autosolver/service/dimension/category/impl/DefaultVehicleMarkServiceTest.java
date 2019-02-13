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
    private List<VehicleMark> vehicleMarks = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();
    private VehicleMark vehicleMark = new VehicleMark();
    private SimpleDTO simpleDTO = new SimpleDTO();
    private Category category = new Category();


    @Before
    public void beforeTest() {
        category.setId(1L);
        category.setName("Lorry");
        category.setValue(1);

        vehicleMark.setId(1L);
        vehicleMark.setCategory(category);
        vehicleMark.setName("Toyota");
        vehicleMark.setValue(1);
        vehicleMarks.add(vehicleMark);

        simpleDTO.setName(vehicleMark.getName());
        simpleDTO.setValue(vehicleMark.getValue());
        simpleDTOs.add(simpleDTO);
    }

    @Test
    public void getAll() {
        Mockito.when(vehicleMarkDao.findAll()).thenReturn(vehicleMarks);
        List<VehicleMark> actualVehicleMarks = defaultVehicleMarkService.getAll();
        assertEquals(vehicleMarks, actualVehicleMarks);
    }

    @Test
    public void getByCategoryId() {
        Mockito.when(vehicleMarkDao.findByCategoryId(1L)).thenReturn(vehicleMarks);
        List<VehicleMark> actulaVehicleMarks = defaultVehicleMarkService.getByCategoryId(1L);
        assertEquals(vehicleMarks, actulaVehicleMarks);
    }

    @Test
    public void getByCategoryValue() {
        Mockito.when(vehicleMarkDao.findByCategoryValue(1)).thenReturn(vehicleMarks);
        List<VehicleMark> actulaVehicleMarks = defaultVehicleMarkService.getByCategoryValue(1);
        assertEquals(vehicleMarks, actulaVehicleMarks);
    }

    @Test
    public void getByCategoryValueAndNameStartsWithDto() {
        Mockito.when(vehicleMarkDao.findByCategoryValueAndNameStartsWithIgnoreCase(1, "Toy"))
                .thenReturn(vehicleMarks);
        List<SimpleDTO> actualSimpleDTOs = defaultVehicleMarkService
                .getByCategoryValueAndNameStartsWithDto(1, "Toy");
        assertEquals(simpleDTOs, actualSimpleDTOs);
    }

    @Test
    public void saveAll() {
        Mockito.when(vehicleMarkDao.saveAll(vehicleMarks)).thenReturn(vehicleMarks);
        int actualVehicleMarkCount = defaultVehicleMarkService.saveAll(vehicleMarks);
        assertEquals(vehicleMarks.size(), actualVehicleMarkCount);
    }

    @Test
    public void saveAllWithCategory() {
        Mockito.when(vehicleMarkDao.saveAll(vehicleMarks)).thenReturn(vehicleMarks);
        int actualVehicleMarkCount = defaultVehicleMarkService.saveAll(vehicleMarks, category);
        assertEquals(1, actualVehicleMarkCount);

        Mockito.when(defaultVehicleMarkService.getByCategoryId(1L)).thenReturn(vehicleMarks);
        actualVehicleMarkCount = defaultVehicleMarkService.saveAll(vehicleMarks, category);
        assertEquals(0, actualVehicleMarkCount);
    }
}