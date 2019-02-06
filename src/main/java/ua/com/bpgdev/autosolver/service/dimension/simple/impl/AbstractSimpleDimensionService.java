package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.simple.SimpleDimension;
import ua.com.bpgdev.autosolver.service.dimension.simple.SimpleDimensionService;

import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractSimpleDimensionService<T extends SimpleDimension>
        implements SimpleDimensionService<T, SimpleDTO> {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SIMPLE_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

    @Override
    public List<SimpleDTO> getAllDto() {
        return MODEL_MAPPER.map(getAll(), SIMPLE_DTO_TYPE);
    }
}
