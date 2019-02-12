package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.GearBoxDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.GearBox;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultGearBoxServiceTest {
    @Mock
    private GearBoxDao gearBoxDao;
    @InjectMocks
    private DefaultGearBoxService defaultGearBoxService;
    private List<GearBox> gearBoxes = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();
    private GearBox gearBox = new GearBox();
    private SimpleDTO simpleDTO = new SimpleDTO();


    @Before
    public void beforeTest(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Lorry");
        category.setValue(1);

        gearBox.setId(1L);
        gearBox.setCategory(category);
        gearBox.setName("Sedan");
        gearBox.setValue(1);
        gearBoxes.add(gearBox);

        simpleDTO.setName(gearBox.getName());
        simpleDTO.setValue(gearBox.getValue());
        simpleDTOs.add(simpleDTO);
    }

    @Test
    public void getAll() {
        Mockito.when(gearBoxDao.findAll()).thenReturn(gearBoxes);
        List<GearBox> actualGearBoxes = defaultGearBoxService.getAll();
        assertEquals(gearBoxes, actualGearBoxes);
    }

    @Test
    public void getByCategoryId() {
        Mockito.when(gearBoxDao.findByCategoryId(1L)).thenReturn(gearBoxes);
        List<GearBox> actulaGearBoxes = defaultGearBoxService.getByCategoryId(1L);
        assertEquals(gearBoxes, actulaGearBoxes);
    }

    @Test
    public void getByCategoryValue() {
        Mockito.when(gearBoxDao.findByCategoryValue(1)).thenReturn(gearBoxes);
        List<GearBox> actulaGearBoxes = defaultGearBoxService.getByCategoryValue(1);
        assertEquals(gearBoxes, actulaGearBoxes);
    }

    @Test
    public void saveAll() {
        Mockito.when(gearBoxDao.saveAll(gearBoxes)).thenReturn(gearBoxes);
        int actualGearBoxCount = defaultGearBoxService.saveAll(gearBoxes);
        assertEquals(gearBoxes.size(), actualGearBoxCount);
    }
}