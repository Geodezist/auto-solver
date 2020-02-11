package ua.com.bpgdev.autosolver.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.bpgdev.autosolver.dto.AdditionalFilterDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.CityDTO;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.service.dimension.category.BodyStyleService;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.service.dimension.category.DriveTypeService;
import ua.com.bpgdev.autosolver.service.dimension.category.GearBoxService;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleMarkService;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleModelService;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleOptionService;
import ua.com.bpgdev.autosolver.service.dimension.simple.CityService;
import ua.com.bpgdev.autosolver.service.dimension.simple.CountryService;
import ua.com.bpgdev.autosolver.service.dimension.simple.FuelTypeService;
import ua.com.bpgdev.autosolver.service.dimension.simple.UkraineStateService;
import ua.com.bpgdev.autosolver.service.dimension.simple.VehicleColorService;
import ua.com.bpgdev.autosolver.service.filter.AdditionalFilterService;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/dimension", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@ResponseBody
@AllArgsConstructor
public class DimensionController {
    private final CountryService countryService;
    private final FuelTypeService fuelTypeService;
    private final UkraineStateService ukraineStateService;
    private final CityService cityService;
    private final VehicleColorService vehicleColorService;
    private final CategoryService categoryService;
    private final BodyStyleService bodyStyleService;
    private final DriveTypeService driveTypeService;
    private final GearBoxService gearBoxService;
    private final VehicleMarkService vehicleMarkService;
    private final VehicleModelService vehicleModelService;
    private final VehicleOptionService vehicleOptionService;
    private final AdditionalFilterService additionalFilterService;

    @GetMapping(path = "/states/{stateValue}/cities")
    public List<SimpleDTO> getCitiesByUkraineState(@PathVariable int stateValue) {
        return cityService.getAllByUkraineStateValueDto(stateValue);
    }

    @GetMapping(path = "/cities")
    public List<CityDTO> getCities() {
        return cityService.getAllDto();
    }

    @GetMapping(path = "/countries")
    public List<SimpleDTO> getCountries() {
        return countryService.getAllDto();
    }

    @GetMapping(path = "/fuel_types")
    public List<SimpleDTO> getFuelTypes() {
        return fuelTypeService.getAllDto();
    }

    @GetMapping(path = "/states")
    public List<SimpleDTO> getUkraineStates() {
        return ukraineStateService.getAllDto();
    }

    @GetMapping(path = "/colors")
    public List<SimpleDTO> getVehicleColors() {
        return vehicleColorService.getAllDto();
    }

    @GetMapping(path = "/categories/{categoryValue}/body_styles")
    public List<SimpleDTO> getBodyStyles(@PathVariable int categoryValue) {
        return bodyStyleService.getByCategoryValueDto(categoryValue);
    }

    @GetMapping(path = "/categories")
    public List<SimpleDTO> getCategories() {
        return categoryService.getAllDto();
    }

    @GetMapping(path = "/categories/{categoryValue}/drive_types")
    public List<SimpleDTO> getDriveTypes(@PathVariable int categoryValue) {
        return driveTypeService.getByCategoryValueDto(categoryValue);
    }

    @GetMapping(path = "/categories/{categoryValue}/gearboxes")
    public List<SimpleDTO> getGearBoxes(@PathVariable int categoryValue) {
        return gearBoxService.getByCategoryValueDto(categoryValue);
    }

    @GetMapping(path = "/categories/{categoryValue}/marks")
    public List<SimpleDTO> getVehicleMarks(@PathVariable int categoryValue) {
        return vehicleMarkService.getByCategoryValueDto(categoryValue);
    }

    @GetMapping(path = "/categories/{categoryValue}/marks/{searchString}")
    public List<SimpleDTO> getVehicleMarksByPartialName(@PathVariable int categoryValue,
                                                        @PathVariable String searchString) {
        return vehicleMarkService.getByCategoryValueAndNameStartsWithDto(categoryValue, searchString);
    }

    @GetMapping(path = "/categories/{categoryValue}/mark/{markValue}/models")
    public List<SimpleDTO> getVehicleModels(@PathVariable int categoryValue,
                                            @PathVariable int markValue) {
        return vehicleModelService.getByCategoryValueAndMarkValueDto(categoryValue, markValue);
    }

    @GetMapping(path = "/categories/{categoryValue}/options")
    public List<SimpleDTO> getVehicleOptions(@PathVariable int categoryValue) {
        return vehicleOptionService.getByCategoryValueDto(categoryValue);
    }

    @GetMapping(path = "/additional_filters")
    public List<AdditionalFilterDTO> getAdditionalFilters() {
        return additionalFilterService.getAllAdditionalFilterDefinitions();
    }
}
