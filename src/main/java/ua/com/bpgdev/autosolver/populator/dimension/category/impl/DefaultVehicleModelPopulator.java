package ua.com.bpgdev.autosolver.populator.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;
import ua.com.bpgdev.autosolver.populator.dimension.category.VehicleModelPopulator;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleMarkService;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleModelService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.List;

@Component
public class DefaultVehicleModelPopulator extends CategoryCommonPopulator<VehicleModel> implements VehicleModelPopulator {
    private static final String MARK_API_ENTITY = "marks/";
    private static final String VEHICLE_MODEL_API_ENTITY = "/models";
    private VehicleMarkService vehicleMarkService;
    private VehicleModelService vehicleModelService;

    @Autowired
    public DefaultVehicleModelPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                        CategoryService categoryService,
                                        VehicleMarkService vehicleMarkService,
                                        VehicleModelService vehicleModelService) {
        super(restApiUrlBuilder, categoryService);
        this.vehicleMarkService = vehicleMarkService;
        this.vehicleModelService = vehicleModelService;
    }

    @Override
    public int populateAll() {
        int result = 0;
        List<Category> categories = categoryService.getAll();
        for (Category category : categories) {
            List<VehicleMark> vehicleMarks = vehicleMarkService.getByCategoryId(category.getId());
            for (VehicleMark vehicleMark : vehicleMarks) {
                String apiEntityString = MARK_API_ENTITY + vehicleMark.getValue() + VEHICLE_MODEL_API_ENTITY;
                List<VehicleModel> vehicleModels = getUpstreamData(VehicleModel[].class, category, apiEntityString);
                vehicleModels.forEach(vehicleModel -> vehicleModel.setVehicleMark(vehicleMark));
                result += vehicleModelService.saveAll(vehicleModels);
            }
        }
        return result;
    }

    @Override
    public boolean populateVehicleModel(VehicleModel vehicleModel) {
        return false;
    }
}