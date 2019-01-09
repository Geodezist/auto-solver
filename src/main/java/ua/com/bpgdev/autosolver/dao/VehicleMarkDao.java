package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.Category;
import ua.com.bpgdev.autosolver.entity.VehicleMark;

import java.util.List;

public interface VehicleMarkDao extends PagingAndSortingRepository<VehicleMark, Long> {
    List<VehicleMark> findByCategoryId(Long categoryId);

    VehicleMark findByCategoryIdAndValue(Long categoryId, int value);

    VehicleMark findByCategoryAndValue(Category category, int value);

    VehicleMark findByCategoryIdAndName(Long categoryId, String name);

    VehicleMark findByCategoryAndName(Category category, String name);
}
