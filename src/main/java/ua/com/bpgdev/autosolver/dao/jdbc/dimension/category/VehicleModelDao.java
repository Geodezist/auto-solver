package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;

import java.util.List;

public interface VehicleModelDao extends PagingAndSortingRepository<VehicleModel, Long> {
    List<VehicleModel> findByVehicleMarkId(Long vehicleMarkId);

    int countByVehicleMark(VehicleMark vehicleMark);

    VehicleModel findByVehicleMarkIdAndValue(Long vehicleMarkId, int value);
}
