package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.GearBox;

public interface GearBoxDao extends PagingAndSortingRepository<GearBox, Long> {
    GearBox findByCategoryIdAndValue(Long categoryId, int value);

}
