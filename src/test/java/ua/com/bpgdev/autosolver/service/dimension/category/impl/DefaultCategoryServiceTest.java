package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.CategoryDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCategoryServiceTest {
    @Mock
    private CategoryDao categoryDao;
    @InjectMocks
    private DefaultCategoryService defaultCategoryService;
    private List<Category> categories = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();
    private Category category = new Category();
    private SimpleDTO simpleDTO = new SimpleDTO();

    @Before
    public void beforeTest(){
        category.setId(1L);
        category.setName("Lorry");
        category.setValue(1);
        categories.add(category);

        simpleDTO.setName(category.getName());
        simpleDTO.setValue(category.getValue());
        simpleDTOs.add(simpleDTO);
    }


    @Test
    public void getAll() {
        Mockito.when(categoryDao.findAll()).thenReturn(categories);
        List<Category> actualCategories = defaultCategoryService.getAll();
        assertEquals(categories, actualCategories);
    }

    @Test
    public void getAllDto() {
        Mockito.when(categoryDao.findAll()).thenReturn(categories);
        List<SimpleDTO> actualSimpleDTOs = defaultCategoryService.getAllDto();
        assertEquals(simpleDTOs, actualSimpleDTOs);

    }

    @Test
    public void saveAll() {
        Mockito.when(categoryDao.saveAll(categories)).thenReturn(categories);
        int actualCategoryCount = defaultCategoryService.saveAll(categories);
        assertEquals(categories.size(), actualCategoryCount);
    }

    @Test
    public void save() {
        Mockito.when(categoryDao.save(category)).thenReturn(category);
        boolean actualSaveStatus = defaultCategoryService.save(category);
        assertTrue(actualSaveStatus);
    }

    @Test
    public void saveFail() {
        Mockito.when(defaultCategoryService.getAll()).thenReturn(categories);
        boolean actualSaveStatus = defaultCategoryService.save(category);
        assertFalse(actualSaveStatus);
    }
}