package ua.com.bpgdev.autosolver.controller.populator.dimension;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.bpgdev.autosolver.populator.dimension.simple.CityPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.CountryPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.FuelTypePopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.UkraineStatePopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.VehicleColorPopulator;

@RestController
@RequestMapping("/populate/dimension/simple")
@RequiredArgsConstructor
public class SimpleDimensionPopulatorController {
    private final CountryPopulator countryPopulator;
    private final UkraineStatePopulator ukraineStatePopulator;
    private final CityPopulator cityPopulator;
    private final FuelTypePopulator fuelTypePopulator;
    private final VehicleColorPopulator vehicleColorPopulator;

    @GetMapping(path = "/countries")
    public String populateCountries() {
        return String.valueOf(countryPopulator.populateAll());
    }

    @GetMapping(path = "/states")
    public String populateUkraineStates() {
        return String.valueOf(ukraineStatePopulator.populateAll());
    }

    @GetMapping(path = "/cities")
    public String populateCities() {
        return String.valueOf(cityPopulator.populateAll());
    }

    @GetMapping(path = "/fuel_types")
    public String populateFuelTypes() {
        return String.valueOf(fuelTypePopulator.populateAll());
    }

    @GetMapping(path = "/vehicle_colors")
    public String populateVehicleColors() {
        return String.valueOf(vehicleColorPopulator.populateAll());
    }
}
