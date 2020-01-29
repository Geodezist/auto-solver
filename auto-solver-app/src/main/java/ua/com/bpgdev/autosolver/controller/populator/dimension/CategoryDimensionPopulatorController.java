package ua.com.bpgdev.autosolver.controller.populator.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.bpgdev.autosolver.populator.dimension.category.*;

@Controller
@RequestMapping("/populate/dimension/category")
@ResponseBody
public class CategoryDimensionPopulatorController {
    private CategoryPopulator categoryPopulator;
    private BodyStylePopulator bodyStylePopulator;
    private DriveTypePopulator driveTypePopulator;
    private GearBoxPopulator gearBoxPopulator;
    private VehicleMarkPopulator vehicleMarkPopulator;
    private VehicleModelPopulator vehicleModelPopulator;
    private VehicleOptionPopulator vehicleOptionPopulator;

    @Autowired
    public CategoryDimensionPopulatorController(CategoryPopulator categoryPopulator,
                                                BodyStylePopulator bodyStylePopulator,
                                                DriveTypePopulator driveTypePopulator,
                                                GearBoxPopulator gearBoxPopulator,
                                                VehicleMarkPopulator vehicleMarkPopulator,
                                                VehicleModelPopulator vehicleModelPopulator,
                                                VehicleOptionPopulator vehicleOptionPopulator) {
        this.categoryPopulator = categoryPopulator;
        this.bodyStylePopulator = bodyStylePopulator;
        this.driveTypePopulator = driveTypePopulator;
        this.gearBoxPopulator = gearBoxPopulator;
        this.vehicleMarkPopulator = vehicleMarkPopulator;
        this.vehicleModelPopulator = vehicleModelPopulator;
        this.vehicleOptionPopulator = vehicleOptionPopulator;
    }

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
