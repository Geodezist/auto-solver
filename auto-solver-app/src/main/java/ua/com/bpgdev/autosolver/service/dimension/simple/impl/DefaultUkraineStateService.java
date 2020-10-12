package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.UkraineStateDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;
import ua.com.bpgdev.autosolver.service.dimension.simple.UkraineStateService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUkraineStateService
        extends AbstractSimpleDimensionService<UkraineState>
        implements UkraineStateService {
    private final UkraineStateDao ukraineStateDao;

    @Override
    public List<UkraineState> getAll() {
        List<UkraineState> ukraineStates = new ArrayList<>();
        ukraineStateDao.findAll().forEach(ukraineStates::add);
        logger.debug("Getting all UkraineStates from DAO. Count of objects - {}"
                , ukraineStates.size());
        return ukraineStates;
    }

    @Override
    public int saveAll(List<UkraineState> entities) {
        filterEntities(entities);
        ukraineStateDao.saveAll(entities);
        return entities.size();
    }
}
