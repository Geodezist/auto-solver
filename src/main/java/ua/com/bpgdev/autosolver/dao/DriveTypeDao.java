package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.DriveType;

public interface DriveTypeDao extends PagingAndSortingRepository<DriveType, Long> {
    DriveType findByCategoryIdAndValue(Long categoryId, int value);
}
