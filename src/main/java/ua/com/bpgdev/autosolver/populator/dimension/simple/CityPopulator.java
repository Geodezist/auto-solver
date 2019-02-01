package ua.com.bpgdev.autosolver.populator.dimension.simple;

import ua.com.bpgdev.autosolver.entity.dimension.simple.City;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface CityPopulator extends Populator {
    boolean populateCity(City city);
}
