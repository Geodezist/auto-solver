package ua.com.bpgdev.autosolver.populator.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.bpgdev.autosolver.entity.dimension.simple.Country;
import ua.com.bpgdev.autosolver.populator.dimension.SimpleCommonPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.CountryPopulator;
import ua.com.bpgdev.autosolver.service.dimension.simple.CountryService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

@Component
public class DefaultCountryPopulator extends SimpleCommonPopulator<Country> implements CountryPopulator {
    private static final String COUNTRY_API_ENTITY = "countries";
    private CountryService countryService;

    @Autowired
    public DefaultCountryPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                   CountryService countryService,
                                   RestTemplate restTemplate) {
        super(restApiUrlBuilder, restTemplate);
        this.countryService = countryService;
    }

    @Override
    public int populateAll() {
        return countryService.saveAll(getUpstreamData(Country[].class, COUNTRY_API_ENTITY));
    }
}

