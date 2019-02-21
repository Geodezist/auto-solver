package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleMarkDao;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.VehicleModelDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultVehicleModelServiceTest {
    @Mock
    private VehicleModelDao vehicleModelDao;
    @Mock
    private VehicleMarkDao vehicleMarkDao;
    @InjectMocks
    private DefaultVehicleModelService defaultVehicleModelService;
    private List<VehicleModel> vehicleModels = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();
    private VehicleMark vehicleMark = new VehicleMark();

    @Before
    public void beforeTest() {
        Category category = new Category();
        category.setId(3000L);
        category.setName("Lorry");
        category.setValue(3000);

        vehicleMark.setId(200L);
        vehicleMark.setCategory(category);
        vehicleMark.setName("Toyota");
        vehicleMark.setValue(200);

        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(1L);
        vehicleModel.setVehicleMark(vehicleMark);
        vehicleModel.setName("Corolla");
        vehicleModel.setValue(1);

        vehicleModels.add(vehicleModel);

        SimpleDTO simpleDTO = new SimpleDTO();
        simpleDTO.setName("Corolla");
        simpleDTO.setValue(1);
        simpleDTOs.add(simpleDTO);
    }


    @Test
    public void getByCategoryValueAndMarkValueDto() {
        Mockito.when(vehicleModelDao.findByVehicleMarkId(200L, Sort.by("name").ascending())).thenReturn(vehicleModels);
        Mockito.when(vehicleMarkDao.findByCategoryValueAndValue(3000, 200)).thenReturn(vehicleMark);
        List<SimpleDTO> actualSimpleDTOs = defaultVehicleModelService
                .getByCategoryValueAndMarkValueDto(3000, 200);
        assertEquals(simpleDTOs, actualSimpleDTOs);
    }

    @Test
    public void saveAll() {
        Mockito.when(vehicleModelDao.saveAll(vehicleModels)).thenReturn(vehicleModels);
        int actualVehicleModelCount = defaultVehicleModelService.saveAll(vehicleModels);
        assertEquals(vehicleModels.size(), actualVehicleModelCount);
    }
}