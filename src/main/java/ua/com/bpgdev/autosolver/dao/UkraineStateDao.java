package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.UkraineState;

public interface UkraineStateDao
        extends SimpleDimensionDao<UkraineState>, PagingAndSortingRepository<UkraineState, Long> {
}
