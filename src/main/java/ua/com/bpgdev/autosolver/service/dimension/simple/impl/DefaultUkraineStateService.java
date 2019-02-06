package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.UkraineStateDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;
import ua.com.bpgdev.autosolver.service.dimension.simple.UkraineStateService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultUkraineStateService
        extends AbstractSimpleDimensionService<UkraineState>
        implements UkraineStateService {
    private UkraineStateDao ukraineStateDao;

    @Autowired
    public DefaultUkraineStateService(UkraineStateDao ukraineStateDao) {
        this.ukraineStateDao = ukraineStateDao;
    }

    @Override
    public List<UkraineState> getAll() {
        List<UkraineState> ukraineStates = new ArrayList<>();
        ukraineStateDao.findAll().forEach(ukraineStates::add);
        return ukraineStates;
    }

    @Override
    public int saveAll(List<UkraineState> entities) {
        entities.removeAll(getAll());
        ukraineStateDao.saveAll(entities);
        return entities.size();
    }
}
