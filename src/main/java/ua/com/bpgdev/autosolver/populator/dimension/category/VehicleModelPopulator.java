package ua.com.bpgdev.autosolver.populator.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface VehicleModelPopulator extends Populator {
    boolean populateVehicleModel(VehicleModel vehicleModel);
}
