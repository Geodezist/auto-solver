package ua.com.bpgdev.autosolver.populator.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleOption;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface VehicleOptionPopulator extends Populator {
    boolean populateVehicleOption(VehicleOption vehicleOption);
}
