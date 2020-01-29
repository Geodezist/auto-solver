package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.DriveTypeDao;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.DriveType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ua.com.bpgdev.autosolver.service.dimension.category.impl.AbstractDimensionWithCategoryService.SORT_BY_VALUE_ASC;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDriveTypeServiceTest {
    @Mock
    private DriveTypeDao driveTypeDao;
    @InjectMocks
    private DefaultDriveTypeService defaultDriveTypeService;
    private List<DriveType> driveTypes = new ArrayList<>();
    private List<SimpleDTO> simpleDTOs = new ArrayList<>();
    private DriveType driveType = new DriveType();
    private SimpleDTO simpleDTO = new SimpleDTO();


    @Before
    public void beforeTest() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Lorry");
        category.setValue(1);

        driveType.setId(1L);
        driveType.setCategory(category);
        driveType.setName("Sedan");
        driveType.setValue(1);
        driveTypes.add(driveType);

        simpleDTO.setName(driveType.getName());
        simpleDTO.setValue(driveType.getValue());
        simpleDTOs.add(simpleDTO);
    }

    @Test
    public void getAll() {
        Mockito.when(driveTypeDao.findAll(SORT_BY_VALUE_ASC)).thenReturn(driveTypes);
        List<DriveType> actualDriveTypes = defaultDriveTypeService.getAll();
        assertEquals(driveTypes, actualDriveTypes);
    }

    @Test
    public void getByCategoryId() {
        Mockito.when(driveTypeDao.findByCategoryId(1L, SORT_BY_VALUE_ASC)).thenReturn(driveTypes);
        List<DriveType> actulaDriveTypes = defaultDriveTypeService.getByCategoryId(1L);
        assertEquals(driveTypes, actulaDriveTypes);
    }

    @Test
    public void getByCategoryValue() {
        Mockito.when(driveTypeDao.findByCategoryValue(1, SORT_BY_VALUE_ASC)).thenReturn(driveTypes);
        List<DriveType> actulaDriveTypes = defaultDriveTypeService.getByCategoryValue(1);
        assertEquals(driveTypes, actulaDriveTypes);
    }

    @Test
    public void saveAll() {
        Mockito.when(driveTypeDao.saveAll(driveTypes)).thenReturn(driveTypes);
        int actualDriveTypeCount = defaultDriveTypeService.saveAll(driveTypes);
        assertEquals(driveTypes.size(), actualDriveTypeCount);
    }
}