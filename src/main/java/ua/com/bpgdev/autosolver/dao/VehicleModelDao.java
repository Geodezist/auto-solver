package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.VehicleModel;

import java.util.List;

public interface VehicleModelDao extends PagingAndSortingRepository<VehicleModel, Long> {
    List<VehicleModel> findByVehicleMarkId(Long vehicleMarkId);
    VehicleModel findByVehicleMarkIdAndValue(Long vehicleMarkId, int value);
}
