package ua.com.bpgdev.autosolver.service.dimension.simple;

import java.util.List;

public interface SimpleDimensionService<T> {
    List<T> getAll();

    int saveAll(List<T> entities);

    boolean save(T entity);
}
