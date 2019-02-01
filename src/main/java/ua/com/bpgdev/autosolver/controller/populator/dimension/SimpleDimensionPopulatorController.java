package ua.com.bpgdev.autosolver.controller.populator.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.bpgdev.autosolver.populator.dimension.simple.*;

@Controller
@RequestMapping("/populate/dimension/simple")
@ResponseBody
public class SimpleDimensionPopulatorController {
    private CountryPopulator countryPopulator;
    private UkraineStatePopulator ukraineStatePopulator;
    private CityPopulator cityPopulator;
    private FuelTypePopulator fuelTypePopulator;
    private VehicleColorPopulator vehicleColorPopulator;


    @Autowired
    public SimpleDimensionPopulatorController(CountryPopulator countryPopulator,
                                              UkraineStatePopulator ukraineStatePopulator,
                                              CityPopulator cityPopulator,
                                              FuelTypePopulator fuelTypePopulator,
                                              VehicleColorPopulator vehicleColorPopulator
    ) {
        this.countryPopulator = countryPopulator;
        this.ukraineStatePopulator = ukraineStatePopulator;
        this.cityPopulator = cityPopulator;
        this.fuelTypePopulator = fuelTypePopulator;
        this.vehicleColorPopulator = vehicleColorPopulator;
    }

    @RequestMapping(path = "/countries", method = RequestMethod.GET)
    public String populateCountries() {
        return String.valueOf(countryPopulator.populateAll());
    }

    @RequestMapping(path = "/states", method = RequestMethod.GET)
    public String populateUkraineStates() {
        return String.valueOf(ukraineStatePopulator.populateAll());
    }

    @RequestMapping(path = "/cities", method = RequestMethod.GET)
    public String populateCities() {
        return String.valueOf(cityPopulator.populateAll());
    }

    @RequestMapping(path = "/fuel_types", method = RequestMethod.GET)
    public String populateFuelTypes() {
        return String.valueOf(fuelTypePopulator.populateAll());
    }

    @RequestMapping(path = "/vehicle_colors", method = RequestMethod.GET)
    public String populateVehicleColors() {
        return String.valueOf(vehicleColorPopulator.populateAll());
    }
}
