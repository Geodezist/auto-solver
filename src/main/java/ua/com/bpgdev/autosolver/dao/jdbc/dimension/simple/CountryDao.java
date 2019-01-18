package ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.bpgdev.autosolver.entity.dimension.simple.Country;

public interface CountryDao extends SimpleDimensionDao<Country>, PagingAndSortingRepository<Country, Long> {
}
