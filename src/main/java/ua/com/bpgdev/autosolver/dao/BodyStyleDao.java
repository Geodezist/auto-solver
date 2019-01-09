package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.BodyStyle;

import java.util.List;

public interface BodyStyleDao extends PagingAndSortingRepository<BodyStyle, Long> {
    List<BodyStyle> findByCategoryId(Long categoryId);
    BodyStyle findByCategoryIdAndValue(Long categoryId, int value);
}
