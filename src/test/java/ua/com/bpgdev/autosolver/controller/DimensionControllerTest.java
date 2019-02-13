package ua.com.bpgdev.autosolver.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.bpgdev.autosolver.dto.dimension.simple.CityDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.service.dimension.category.*;
import ua.com.bpgdev.autosolver.service.dimension.simple.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DimensionControllerTest {
    @Mock
    private CountryService countryService;
    @Mock
    private FuelTypeService fuelTypeService;
    @Mock
    private UkraineStateService ukraineStateService;
    @Mock
    private CityService cityService;
    @Mock
    private VehicleColorService vehicleColorService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private BodyStyleService bodyStyleService;
    @Mock
    private DriveTypeService driveTypeService;
    @Mock
    private GearBoxService gearBoxService;
    @Mock
    private VehicleMarkService vehicleMarkService;
    @Mock
    private VehicleOptionService vehicleOptionService;
    @Mock
    private VehicleModelService vehicleModelService;
    @InjectMocks
    private DimensionController dimensionController;

    private List<SimpleDTO> expectedResult = new ArrayList<>();

    @Before
    public void beforeTest(){
        SimpleDTO simpleDTO = new SimpleDTO();
        simpleDTO.setName("Test Data");
        simpleDTO.setValue(1);

        expectedResult.add(simpleDTO);
    }

    @Test
    public void getCitiesByUkraineState() {
        Mockito.when(cityService.getAllByUkraineStateValueDto(1)).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getCitiesByUkraineState(1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getCities() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setUkraineStateName("Kyiv");
        cityDTO.setUkraineStateValue(1);
        cityDTO.setName("Kyiv");
        cityDTO.setValue(1);

        List<CityDTO> cityDTOs = new ArrayList<>();
        cityDTOs.add(cityDTO);

        Mockito.when(cityService.getAllDto()).thenReturn(cityDTOs);
        List<CityDTO> actualResult = dimensionController.getCities();
        assertEquals(cityDTOs, actualResult);
    }

    @Test
    public void getCountries() {
        Mockito.when(countryService.getAllDto()).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getCountries();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getFuelTypes() {
        Mockito.when(fuelTypeService.getAllDto()).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getFuelTypes();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUkraineStates() {
        Mockito.when(ukraineStateService.getAllDto()).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getUkraineStates();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getVehicleColors() {
        Mockito.when(vehicleColorService.getAllDto()).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getVehicleColors();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getBodyStyles() {
        Mockito.when(bodyStyleService.getByCategoryValueDto(1)).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getBodyStyles(1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getCategories() {
        Mockito.when(categoryService.getAllDto()).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getCategories();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getDriveTypes() {
        Mockito.when(driveTypeService.getByCategoryValueDto(1)).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getDriveTypes(1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getGearBoxes() {
        Mockito.when(gearBoxService.getByCategoryValueDto(1)).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getGearBoxes(1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getVehicleMarks() {
        Mockito.when(vehicleMarkService.getByCategoryValueDto(1)).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getVehicleMarks(1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getVehicleMarksByPartialName() {
        Mockito.when(vehicleMarkService.getByCategoryValueAndNameStartsWithDto(1, "Toy"))
                .thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getVehicleMarksByPartialName(1, "Toy");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getVehicleModels() {
        Mockito.when(vehicleModelService.getByCategoryValueAndMarkValueDto(1, 1)).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getVehicleModels(1, 1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getVehicleOptions() {
        Mockito.when(vehicleOptionService.getByCategoryValueDto(1)).thenReturn(expectedResult);
        List<SimpleDTO> actualResult = dimensionController.getVehicleOptions(1);
        assertEquals(expectedResult, actualResult);
    }
}