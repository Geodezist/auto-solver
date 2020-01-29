package ua.com.bpgdev.autosolver.service.dimension.category;

import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;

import java.util.List;

public interface VehicleModelService {
    int saveAll(List<VehicleModel> vehicleModels);

    int getCount(VehicleMark vehicleMark);

    List<SimpleDTO> getByCategoryValueAndMarkValueDto(int categoryValue, int markValue);
}
