package ua.com.bpgdev.autosolver.populator.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleOption;
import ua.com.bpgdev.autosolver.populator.dimension.category.VehicleOptionPopulator;
import ua.com.bpgdev.autosolver.service.dimension.category.CategoryService;
import ua.com.bpgdev.autosolver.service.dimension.category.VehicleOptionService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

import java.util.List;

@Component
public class DefaultVehicleOptionPopulator extends CategoryCommonPopulator<VehicleOption> implements VehicleOptionPopulator {
    private static final String VEHICLE_OPTION_API_ENTITY = "options";
    private VehicleOptionService vehicleOptionService;

    @Autowired
    public DefaultVehicleOptionPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                         CategoryService categoryService,
                                         VehicleOptionService vehicleOptionService) {
        super(restApiUrlBuilder, categoryService);
        this.vehicleOptionService = vehicleOptionService;
    }

    @Override
    public int populateAll() {
        List<Category> categories = categoryService.getAll();
        int result = 0;
        for (Category category : categories) {
            List<VehicleOption> vehicleOptions = getUpstreamData(VehicleOption[].class, category, VEHICLE_OPTION_API_ENTITY);
            vehicleOptions.forEach(vehicleOption -> vehicleOption.setCategory(category));
            result += vehicleOptionService.saveAll(vehicleOptions);
        }
        return result;
    }
}
