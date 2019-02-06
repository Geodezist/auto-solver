package ua.com.bpgdev.autosolver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.bpgdev.autosolver.dto.dimension.simple.CityDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.service.dimension.category.*;
import ua.com.bpgdev.autosolver.service.dimension.simple.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/dimension", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
@ResponseBody
public class DimensionController {
    private CountryService countryService;
    private FuelTypeService fuelTypeService;
    private UkraineStateService ukraineStateService;
    private CityService cityService;
    private VehicleColorService vehicleColorService;
    private CategoryService categoryService;
    private BodyStyleService bodyStyleService;
    private DriveTypeService driveTypeService;
    private GearBoxService gearBoxService;
    private VehicleMarkService vehicleMarkService;
    private VehicleModelService vehicleModelService;
    private VehicleOptionService vehicleOptionService;

    @Autowired
    public DimensionController(CountryService countryService,
                               FuelTypeService fuelTypeService,
                               UkraineStateService ukraineStateService,
                               CityService cityService,
                               VehicleColorService vehicleColorService,
                               CategoryService categoryService,
                               BodyStyleService bodyStyleService,
                               DriveTypeService driveTypeService,
                               GearBoxService gearBoxService,
                               VehicleMarkService vehicleMarkService,
                               VehicleModelService vehicleModelService,
                               VehicleOptionService vehicleOptionService) {
        this.countryService = countryService;
        this.fuelTypeService = fuelTypeService;
        this.ukraineStateService = ukraineStateService;
        this.cityService = cityService;
        this.vehicleColorService = vehicleColorService;
        this.categoryService = categoryService;
        this.bodyStyleService = bodyStyleService;
        this.driveTypeService = driveTypeService;
        this.gearBoxService = gearBoxService;
        this.vehicleMarkService = vehicleMarkService;
        this.vehicleModelService = vehicleModelService;
        this.vehicleOptionService = vehicleOptionService;
    }

    @RequestMapping(path = "/states/{stateValue}/cities")
    public List<SimpleDTO> getCitiesByUkraineState(@PathVariable int stateValue) {
        return cityService.getAllByUkraineStateValueDto(stateValue);
    }

    @RequestMapping(path = "/cities")
    public List<CityDTO> getCities() {
        return cityService.getAllDto();
    }

    @RequestMapping(path = "/countries")
    public List<SimpleDTO> getCountries() {
        return countryService.getAllDto();
    }

    @RequestMapping(path = "/fuel_types")
    public List<SimpleDTO> getFuelTypes() {
        return fuelTypeService.getAllDto();
    }

    @RequestMapping(path = "/states")
    public List<SimpleDTO> getUkraineStates() {
        return ukraineStateService.getAllDto();
    }

    @RequestMapping(path = "/colors")
    public List<SimpleDTO> getVehicleColors() {
        return vehicleColorService.getAllDto();
    }

    @RequestMapping(path = "/categories/{categoryValue}/body_styles")
    public List<SimpleDTO> getBodyStyles(@PathVariable int categoryValue) {
        return bodyStyleService.getByCategoryValueDto(categoryValue);
    }

    @RequestMapping(path = "/categories")
    public List<SimpleDTO> getCategories() {
        return categoryService.getAllDto();
    }

    @RequestMapping(path = "/categories/{categoryValue}/drive_types")
    public List<SimpleDTO> getDriveTypes(@PathVariable int categoryValue) {
        return driveTypeService.getByCategoryValueDto(categoryValue);
    }

    @RequestMapping(path = "/categories/{categoryValue}/gearboxes")
    public List<SimpleDTO> getGearBoxes(@PathVariable int categoryValue) {
        return gearBoxService.getByCategoryValueDto(categoryValue);
    }

    @RequestMapping(path = "/categories/{categoryValue}/marks")
    public List<SimpleDTO> getVehicleMarks(@PathVariable int categoryValue) {
        return vehicleMarkService.getByCategoryValueDto(categoryValue);
    }

    @RequestMapping(path = "/categories/{categoryValue}/marks/{searchString}")
    public List<SimpleDTO> getVehicleMarksByPartialName(@PathVariable int categoryValue,
                                                        @PathVariable String searchString) {
        return vehicleMarkService.getByCategoryValueAndNameStartsWithDto(categoryValue, searchString);
    }

    @RequestMapping(path = "/categories/{categoryValue}/mark/{markValue}/models")
    public List<SimpleDTO> getVehicleModels(@PathVariable int categoryValue,
                                            @PathVariable int markValue) {
        return vehicleModelService.getByCategoryValueAndMarkValueDto(categoryValue, markValue);
    }

    @RequestMapping(path = "/categories/{categoryValue}/options")
    public List<SimpleDTO> getVehicleOptions(@PathVariable int categoryValue) {
        return vehicleOptionService.getByCategoryValueDto(categoryValue);
    }

}