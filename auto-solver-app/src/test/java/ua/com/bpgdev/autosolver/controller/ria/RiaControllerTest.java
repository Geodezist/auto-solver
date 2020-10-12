package ua.com.bpgdev.autosolver.controller.ria;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;
import ua.com.bpgdev.autosolver.entity.fact.SourceCar;
import ua.com.bpgdev.autosolver.service.fact.SourceCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaCarService;
import ua.com.bpgdev.autosolver.service.ria.RiaSearchResultService;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RiaControllerTest {
    @Mock
    private RiaSearchResultService riaSearchResultService;
    @Mock
    private RiaCarService riaCarService;
    @Mock
    private SourceCarService sourceCarService;
    @InjectMocks
    private RiaController riaController;
    private RiaCarDTO expectedRiaCarDTO;
    private Set<Integer> expectedIds;

    @Before
    public void beforeTest() {
        expectedRiaCarDTO = new RiaCarDTO();
        expectedRiaCarDTO.setCarId(1);
        expectedRiaCarDTO.setYear(2000);
        expectedRiaCarDTO.setMileage(100);
        expectedRiaCarDTO.setPriceUSD(10);
        expectedRiaCarDTO.setCategoryValue(1);
        expectedRiaCarDTO.setBodystyleValue(1);
        expectedRiaCarDTO.setMarkValue(1);
        expectedRiaCarDTO.setModelValue(1);
        expectedRiaCarDTO.setDescription("Description");
        expectedRiaCarDTO.setFuelTypeName("FuelTypeName");
        expectedRiaCarDTO.setFuelTypeNameEng("FuelTypeNameEng");
        expectedRiaCarDTO.setGearboxName("GearboxName");
        expectedRiaCarDTO.setUkraineStateName("UkraineStateName");
        expectedRiaCarDTO.setCityName("CityName");

        expectedIds = new HashSet<>();
        expectedIds.add(1);
        expectedIds.add(2);
    }

    @Test
    public void getCar() {
        Mockito.when(riaCarService.getCar(1)).thenReturn(expectedRiaCarDTO);
        RiaCarDTO actualRiaCarDTO = riaController.getCar(1);
        assertEquals(expectedRiaCarDTO, actualRiaCarDTO);
    }
//
//    @Test
//    public void getAllCarIds() {
//        Mockito.when(riaSearchResultService.getSearchResult("test")).thenReturn(expectedIds);
//        Set<Integer> actualIds = riaController.getAllCarIds("test");
//        assertEquals(expectedIds, actualIds);
//    }

    @Test
    public void saveAllCars() {
/*
        List<Integer> expectedExistingCarIds = new ArrayList<>();
        expectedExistingCarIds.add(2);
        List<Integer> expectedNewCarIds = new ArrayList<>();
        expectedNewCarIds.add(1);
        List<RiaCarDTO> expectedRiaCarDTOs = new ArrayList<>();
        expectedRiaCarDTOs.add(expectedRiaCarDTO);
        Mockito.when(riaSearchResultService.getSearchResult("test")).thenReturn(expectedIds);
        Mockito.when(sourceCarService.findAllByCarIdIn(new ArrayList<>(expectedIds))).thenReturn(expectedExistingCarIds);
        Mockito.when(riaCarService.getAll(expectedNewCarIds)).thenReturn(expectedRiaCarDTOs);
        Mockito.when(sourceCarService.saveAllDTO(expectedRiaCarDTOs)).thenReturn(1);
        List<SourceCar> actualRiaCarDTOCount  = riaController.saveAllCars("test");
        assertEquals(expectedRiaCarDTOs, actualRiaCarDTOCount);
*/
    }
}
