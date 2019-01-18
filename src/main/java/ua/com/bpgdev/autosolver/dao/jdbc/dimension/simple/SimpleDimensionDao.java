package ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
interface SimpleDimensionDao<T> extends Repository<T, Long> {
    T findByName(String value);
    T findByValue(int value);
}
