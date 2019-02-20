package ua.com.bpgdev.autosolver.service.ria.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.rest.ria.RiaCarDao;
import ua.com.bpgdev.autosolver.dto.ria.RiaCarDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRiaCarServiceTest {
    @Mock
    private RiaCarDao riaCarDao;
    @InjectMocks
    private DefaultRiaCarService defaultRiaCarService;
    private RiaCarDTO expectedRiaCarDTO;
    private List<RiaCarDTO> expectedRiaCarDTOs;

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
    }


    @Test
    public void getCar() throws InterruptedException {
        Mockito.when(riaCarDao.getCar(1)).thenReturn(expectedRiaCarDTO);
        RiaCarDTO actualRiaCarDTO = defaultRiaCarService.getCar(1);
        assertEquals(expectedRiaCarDTO, actualRiaCarDTO);
    }

    @Test
    public void getAll() {
        List<Integer> riaCarDTOIds = new ArrayList<>();
        riaCarDTOIds.add(1);
        Mockito.when(riaCarDao.getAll(riaCarDTOIds)).thenReturn(expectedRiaCarDTOs);
        List<RiaCarDTO> actualRiaCarDTOs = defaultRiaCarService.getAll(riaCarDTOIds);
        assertEquals(expectedRiaCarDTOs, actualRiaCarDTOs);
    }
}