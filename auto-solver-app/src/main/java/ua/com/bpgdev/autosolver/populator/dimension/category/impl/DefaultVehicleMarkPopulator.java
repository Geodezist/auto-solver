package ua.com.bpgdev.autosolver.populator.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.populator.dimension.category.VehicleMarkPopulator;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleMarkService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.List;

@Component
public class DefaultVehicleMarkPopulator extends CategoryCommonPopulator<VehicleMark> implements VehicleMarkPopulator {
    private static final String VEHICLE_MARK_API_ENTITY = "marks";
    private VehicleMarkService vehicleMarkService;

    @Autowired
    public DefaultVehicleMarkPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                       CategoryService categoryService,
                                       VehicleMarkService vehicleMarkService) {
        super(restApiUrlBuilder, categoryService);
        this.vehicleMarkService = vehicleMarkService;
    }

    @Override
    public int populateAll() {
        List<Category> categories = categoryService.getAll();
        int result = 0;
        for (Category category : categories) {
            List<VehicleMark> vehicleMarks = getUpstreamData(VehicleMark[].class, category, VEHICLE_MARK_API_ENTITY);
            vehicleMarks.forEach(vehicleMark -> vehicleMark.setCategory(category));
            result += vehicleMarkService.saveAll(vehicleMarks, category);
        }
        return result;
    }
}
