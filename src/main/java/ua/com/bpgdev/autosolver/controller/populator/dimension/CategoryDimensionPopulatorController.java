package ua.com.bpgdev.autosolver.controller.populator.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public String populateCategories() {
        return String.valueOf(categoryPopulator.populateAll());
    }

    @RequestMapping(path = "/bodystyles", method = RequestMethod.GET)
    public String populateBodyStyle() {
        return String.valueOf(bodyStylePopulator.populateAll());
    }

    @RequestMapping(path = "/drive_types", method = RequestMethod.GET)
    public String populateDriveTypes() {
        return String.valueOf(driveTypePopulator.populateAll());
    }

    @RequestMapping(path = "/gearboxes", method = RequestMethod.GET)
    public String populateGearBoxes() {
        return String.valueOf(gearBoxPopulator.populateAll());
    }

    @RequestMapping(path = "/vehicle_marks", method = RequestMethod.GET)
    public String populateVehicleMarks() {
        return String.valueOf(vehicleMarkPopulator.populateAll());
    }

    @RequestMapping(path = "/vehicle_models", method = RequestMethod.GET)
    public String populateVehicleModels() {
        return String.valueOf(vehicleModelPopulator.populateAll());
    }

    @RequestMapping(path = "/vehicle_options", method = RequestMethod.GET)
    public String populateVehicleOptions() {
        return String.valueOf(vehicleOptionPopulator.populateAll());
    }
}
