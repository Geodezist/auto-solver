package ua.com.bpgdev.autosolver.controller.populator.dimension;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.bpgdev.autosolver.populator.dimension.category.BodyStylePopulator;
import ua.com.bpgdev.autosolver.populator.dimension.category.CategoryPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.category.DriveTypePopulator;
import ua.com.bpgdev.autosolver.populator.dimension.category.GearBoxPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.category.VehicleMarkPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.category.VehicleModelPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.category.VehicleOptionPopulator;

@RestController
@RequestMapping("/populate/dimension/category")
@RequiredArgsConstructor
public class CategoryDimensionPopulatorController {
    private final CategoryPopulator categoryPopulator;
    private final BodyStylePopulator bodyStylePopulator;
    private final DriveTypePopulator driveTypePopulator;
    private final GearBoxPopulator gearBoxPopulator;
    private final VehicleMarkPopulator vehicleMarkPopulator;
    private final VehicleModelPopulator vehicleModelPopulator;
    private final VehicleOptionPopulator vehicleOptionPopulator;

    @GetMapping(path = "/categories")
    public String populateCategories() {
        return String.valueOf(categoryPopulator.populateAll());
    }

    @GetMapping(path = "/bodystyles")
    public String populateBodyStyle() {
        return String.valueOf(bodyStylePopulator.populateAll());
    }

    @GetMapping(path = "/drive_types")
    public String populateDriveTypes() {
        return String.valueOf(driveTypePopulator.populateAll());
    }

    @GetMapping(path = "/gearboxes")
    public String populateGearBoxes() {
        return String.valueOf(gearBoxPopulator.populateAll());
    }

    @GetMapping(path = "/vehicle_marks")
    public String populateVehicleMarks() {
        return String.valueOf(vehicleMarkPopulator.populateAll());
    }

    @GetMapping(path = "/vehicle_models")
    public String populateVehicleModels() {
        return String.valueOf(vehicleModelPopulator.populateAllAbsent());
    }

    @GetMapping(path = "/vehicle_models/{categoryValue}")
    public String populateVehicleModelsByCategory(@PathVariable int categoryValue) {
        return String.valueOf(vehicleModelPopulator.populateAllByCategory(categoryValue));
    }

    @GetMapping(path = "/vehicle_options")
    public String populateVehicleOptions() {
        return String.valueOf(vehicleOptionPopulator.populateAll());
    }
}
