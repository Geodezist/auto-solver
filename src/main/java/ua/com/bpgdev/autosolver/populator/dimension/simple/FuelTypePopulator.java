package ua.com.bpgdev.autosolver.populator.dimension.simple;

import ua.com.bpgdev.autosolver.entity.dimension.simple.FuelType;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface FuelTypePopulator extends Populator {
    boolean populateFuelType(FuelType fuelType);
}
