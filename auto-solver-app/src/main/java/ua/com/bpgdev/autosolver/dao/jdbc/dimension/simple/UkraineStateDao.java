package ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;

public interface UkraineStateDao
        extends SimpleDimensionDao<UkraineState>, PagingAndSortingRepository<UkraineState, Long> {
}
