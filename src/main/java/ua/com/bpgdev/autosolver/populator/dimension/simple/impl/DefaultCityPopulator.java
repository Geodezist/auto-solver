package ua.com.bpgdev.autosolver.populator.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.dimension.simple.City;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;
import ua.com.bpgdev.autosolver.populator.dimension.SimpleCommonPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.CityPopulator;
import ua.com.bpgdev.autosolver.service.dimension.simple.CityService;
import ua.com.bpgdev.autosolver.service.dimension.simple.UkraineStateService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.List;

@Component
public class DefaultCityPopulator extends SimpleCommonPopulator<City> implements CityPopulator {
    private static final String UKRAINE_STATE_API_ENTITY = "states";
    private static final String CITY_API_ENTITY = "cities";
    private UkraineStateService ukraineStateService;
    private CityService cityService;

    @Autowired
    public DefaultCityPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                UkraineStateService ukraineStateService,
                                CityService cityService) {
        super(restApiUrlBuilder);
        this.ukraineStateService = ukraineStateService;
        this.cityService = cityService;
    }

    @Override
    public int populateAll() {
        List<UkraineState> ukraineStates = ukraineStateService.getAll();
        int result = 0;
        for (UkraineState ukraineState : ukraineStates) {
            String apiEntityString = UKRAINE_STATE_API_ENTITY + "/" + ukraineState.getValue() + "/" + CITY_API_ENTITY;
            List<City> cities = getUpstreamData(City[].class, apiEntityString);
            cities.forEach(city -> city.setUkraineState(ukraineState));
            result += cityService.saveAll(cities);
        }
        return result;
    }

    @Override
    public boolean populateCity(City city) {
        return cityService.save(city);
    }
}
