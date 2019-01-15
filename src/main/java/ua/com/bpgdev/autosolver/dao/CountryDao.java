package ua.com.bpgdev.autosolver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.Country;

public interface CountryDao extends SimpleDimensionDao<Country>, PagingAndSortingRepository<Country, Long> {
}
