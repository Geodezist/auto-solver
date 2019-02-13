package ua.com.bpgdev.autosolver.populator.dimension.simple.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.bpgdev.autosolver.entity.dimension.simple.FuelType;
import ua.com.bpgdev.autosolver.populator.dimension.SimpleCommonPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.FuelTypePopulator;
import ua.com.bpgdev.autosolver.service.dimension.simple.FuelTypeService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

@Component
public class DefaultFuelTypePopulator extends SimpleCommonPopulator<FuelType> implements FuelTypePopulator {
    private static final String FUEL_TYPE_API_ENTITY = "type";
    private FuelTypeService fuelTypeService;

    public DefaultFuelTypePopulator(RestApiUrlBuilder restApiUrlBuilder,
                                    FuelTypeService fuelTypeService,
                                    RestTemplate restTemplate) {
        super(restApiUrlBuilder, restTemplate);
        this.fuelTypeService = fuelTypeService;
    }

    @Override
    public int populateAll() {
        return fuelTypeService.saveAll(getUpstreamData(FuelType[].class, FUEL_TYPE_API_ENTITY));
    }
}
