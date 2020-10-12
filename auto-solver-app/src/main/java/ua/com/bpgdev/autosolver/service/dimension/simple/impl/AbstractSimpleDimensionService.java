package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import ua.com.bpgdev.autosolver.dto.dimension.simple.SimpleDTO;
import ua.com.bpgdev.autosolver.entity.dimension.simple.SimpleDimension;
import ua.com.bpgdev.autosolver.service.dimension.simple.SimpleDimensionService;

import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractSimpleDimensionService<T extends SimpleDimension>
        implements SimpleDimensionService<T, SimpleDTO> {

    private final String className = getClass().getSimpleName();
    final Logger logger = LoggerFactory.getLogger(getClass());

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Type SIMPLE_DTO_TYPE = new TypeToken<List<SimpleDTO>>() {
    }.getType();

    @Override
    @Cacheable(value = "simpleDictionaryCache", key = "#root.targetClass")
    public List<SimpleDTO> getAllDto() {
        List<SimpleDTO> result = MODEL_MAPPER.map(getAll(), SIMPLE_DTO_TYPE);
        logger.debug("Getting all DTOs by {}. Count of objects - {}"
                , className
                , result.size());
        return result;
    }

    void filterEntities(List<T> entities) {
        logger.debug("Saving all {}. Count of incoming objects - {}"
                , className
                , entities.size());
        entities.removeAll(getAll());
        logger.debug("Saving all {}. Count of objects after filtering - {}"
                , className
                , entities.size());
    }

    @Override
    @CacheEvict(value = "simpleDictionaryCache", key = "#root.targetClass", condition = "#result != 0")
    public abstract int saveAll(List<T> entities);
}
