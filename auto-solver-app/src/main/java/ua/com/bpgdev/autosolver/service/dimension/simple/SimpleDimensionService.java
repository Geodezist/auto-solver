package ua.com.bpgdev.autosolver.service.dimension.simple;

import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.simple.SimpleDimension;

import java.util.List;

public interface SimpleDimensionService<T extends SimpleDimension, D extends SimpleDTO> {

    List<T> getAll();

    List<D> getAllDto();

    int saveAll(List<T> entities);
}
