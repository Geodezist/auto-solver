package ua.com.bpgdev.autosolver.dao.jdbc.dimension.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;

import java.util.List;

public interface VehicleMarkDao
        extends DimensionWithCategoryDao<VehicleMark>, PagingAndSortingRepository<VehicleMark, Long> {

    List<VehicleMark> findByCategoryValueAndNameStartsWithIgnoreCase(int categoryId, String searchStrng);

    VehicleMark findByCategoryValueAndValue(int categoryValue, int value);
}
