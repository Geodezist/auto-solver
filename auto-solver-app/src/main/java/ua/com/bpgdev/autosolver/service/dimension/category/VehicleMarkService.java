package ua.com.bpgdev.autosolver.service.dimension.category;

import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;

import java.util.List;

public interface VehicleMarkService extends DimensionWithCategoryService<VehicleMark, SimpleDTO> {
    int saveAll(List<VehicleMark> vehicleMarks, Category category);

    List<SimpleDTO> getByCategoryValueAndNameStartsWithDto(int categoryValue, String searchString);
}
