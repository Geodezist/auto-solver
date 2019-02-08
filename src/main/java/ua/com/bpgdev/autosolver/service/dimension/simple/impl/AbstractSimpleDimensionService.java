package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.simple.SimpleDimension;
import ua.com.bpgdev.autosolver.service.dimension.simple.SimpleDimensionService;

import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractSimpleDimensionService<T extends SimpleDimension>
        implements SimpleDimensionService<T, SimpleDTO> {
    private final String className = getClass().getSimpleName();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SIMPLE_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

    @Override
    public List<SimpleDTO> getAllDto() {
        List<SimpleDTO> result = MODEL_MAPPER.map(getAll(), SIMPLE_DTO_TYPE);
        logger.debug("Getting all DTOs by {}. Count of oblects - {}"
                , className
                , result.size());
        return result;
    }

    void filterEntities(List<T> entities){
        logger.debug("Saving all {}. Count of incoming oblects - {}"
                , className
                , entities.size());
        entities.removeAll(getAll());
        logger.debug("Saving all {}. Count of oblects after filtering - {}"
                , className
                , entities.size());
    }
}
