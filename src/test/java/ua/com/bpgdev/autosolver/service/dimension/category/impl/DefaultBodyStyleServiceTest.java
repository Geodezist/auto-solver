package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.BodyStyleDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.BodyStyle;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultBodyStyleServiceTest {
    @Mock
    private BodyStyleDao bodyStyleDao;
    @InjectMocks
    private DefaultBodyStyleService defaultBodyStyleService;
    private List<BodyStyle> bodyStyles = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();
    private BodyStyle bodyStyle = new BodyStyle();
    private SimpleDTO simpleDTO = new SimpleDTO();

    @Before
    public void beforeTest(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Lorry");
        category.setValue(1);

        bodyStyle.setId(1L);
        bodyStyle.setCategory(category);
        bodyStyle.setName("Sedan");
        bodyStyle.setValue(1);
        bodyStyles.add(bodyStyle);

        simpleDTO.setName(bodyStyle.getName());
        simpleDTO.setValue(bodyStyle.getValue());
        simpleDTOs.add(simpleDTO);
    }

    @Test
    public void getAll() {
        Mockito.when(bodyStyleDao.findAll()).thenReturn(bodyStyles);
        List<BodyStyle> actualBodyStyles = defaultBodyStyleService.getAll();
        assertEquals(bodyStyles, actualBodyStyles);
    }

    @Test
    public void getByCategoryId() {
        Mockito.when(bodyStyleDao.findByCategoryId(1L)).thenReturn(bodyStyles);
        List<BodyStyle> actulaBodyStyles = defaultBodyStyleService.getByCategoryId(1L);
        assertEquals(bodyStyles, actulaBodyStyles);
    }

    @Test
    public void getByCategoryIdDto(){
        Mockito.when(defaultBodyStyleService.getByCategoryId(1L)).thenReturn(bodyStyles);
        List<SimpleDTO> actualSimpleDTOs = defaultBodyStyleService.getByCategoryIdDto(1L);
        assertEquals(simpleDTOs, actualSimpleDTOs);
    }

    @Test
    public void getByCategoryValueDto() {
        Mockito.when(defaultBodyStyleService.getByCategoryValue(1)).thenReturn(bodyStyles);
        List<SimpleDTO> actualSimpleDTOs = defaultBodyStyleService.getByCategoryValueDto(1);
        assertEquals(simpleDTOs, actualSimpleDTOs);
    }

    @Test
    public void getByCategoryValue() {
        Mockito.when(bodyStyleDao.findByCategoryValue(1)).thenReturn(bodyStyles);
        List<BodyStyle> actulaBodyStyles = defaultBodyStyleService.getByCategoryValue(1);
        assertEquals(bodyStyles, actulaBodyStyles);
    }

    @Test
    public void saveAll() {
        Mockito.when(bodyStyleDao.saveAll(bodyStyles)).thenReturn(bodyStyles);
        int actualBodyStyleCount = defaultBodyStyleService.saveAll(bodyStyles);
        assertEquals(bodyStyles.size(), actualBodyStyleCount);
    }
}