package ua.com.bpgdev.autosolver.service.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;

import java.util.List;

public interface VehicleModelService {
    List<VehicleModel> getAll();

    List<VehicleModel> getByVehicleMarkId(Long vehicleMarkId);

    int saveAll(List<VehicleModel> vehicleModels);
}
