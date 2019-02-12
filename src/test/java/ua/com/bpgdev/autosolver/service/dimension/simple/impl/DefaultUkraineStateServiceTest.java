package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.UkraineStateDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUkraineStateServiceTest {
    @Mock
    private UkraineStateDao ukraineStateDao;
    @InjectMocks
    private DefaultUkraineStateService defaultUkraineStateService;
    private List<UkraineState> ukraineStates = new ArrayList<>();

    @Before
    public void beforeTest(){
        UkraineState ukraineState = new UkraineState();
        ukraineState.setId(1L);
        ukraineState.setName("Kyiv");
        ukraineState.setValue(1);
        ukraineStates.add(ukraineState);
    }

    @Test
    public void getAll() {
        Mockito.when(ukraineStateDao.findAll()).thenReturn(ukraineStates);
        List<UkraineState> actualUkraineStates = defaultUkraineStateService.getAll();
        assertEquals(ukraineStates, actualUkraineStates);
    }

    @Test
    public void saveAll() {
        Mockito.when(ukraineStateDao.saveAll(ukraineStates)).thenReturn(ukraineStates);
        int actualUkraineStateCount = defaultUkraineStateService.saveAll(ukraineStates);
        assertEquals(ukraineStates.size(), actualUkraineStateCount);
    }
}