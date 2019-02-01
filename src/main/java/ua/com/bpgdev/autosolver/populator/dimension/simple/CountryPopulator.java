package ua.com.bpgdev.autosolver.populator.dimension.simple;

import ua.com.bpgdev.autosolver.entity.dimension.simple.Country;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface CountryPopulator extends Populator {
    boolean populateCountry(Country country);
}
