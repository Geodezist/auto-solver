package ua.com.bpgdev.autosolver.populator.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface VehicleMarkPopulator extends Populator {
    boolean populateVehicleMark(VehicleMark vehicleMark);
}
