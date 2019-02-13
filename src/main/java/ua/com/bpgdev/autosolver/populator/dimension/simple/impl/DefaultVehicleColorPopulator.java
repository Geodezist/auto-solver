package ua.com.bpgdev.autosolver.populator.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.bpgdev.autosolver.entity.dimension.simple.VehicleColor;
import ua.com.bpgdev.autosolver.populator.dimension.SimpleCommonPopulator;
import ua.com.bpgdev.autosolver.populator.dimension.simple.VehicleColorPopulator;
import ua.com.bpgdev.autosolver.service.dimension.simple.VehicleColorService;
import ua.com.bpgdev.autosolver.util.RestApiUrlBuilder;

@Component
public class DefaultVehicleColorPopulator extends SimpleCommonPopulator<VehicleColor> implements VehicleColorPopulator {
    private static final String VEHICLE_COLOR_API_ENTITY = "colors";
    private VehicleColorService vehicleColorService;

    @Autowired
    public DefaultVehicleColorPopulator(RestApiUrlBuilder restApiUrlBuilder,
                                        VehicleColorService vehicleColorService,
                                        RestTemplate restTemplate) {
        super(restApiUrlBuilder, restTemplate);
        this.vehicleColorService = vehicleColorService;
    }

    @Override
    public int populateAll() {
        return vehicleColorService.saveAll(getUpstreamData(VehicleColor[].class, VEHICLE_COLOR_API_ENTITY));
    }
}
