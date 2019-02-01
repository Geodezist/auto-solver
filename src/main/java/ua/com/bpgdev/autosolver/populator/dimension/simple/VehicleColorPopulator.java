package ua.com.bpgdev.autosolver.populator.dimension.simple;

import ua.com.bpgdev.autosolver.entity.dimension.simple.VehicleColor;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface VehicleColorPopulator extends Populator {
    boolean populateVehicleColor(VehicleColor vehicleColor);
}
