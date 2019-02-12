package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.CountryDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.Country;
import ua.com.bpgdev.autosolver.service.dimension.simple.CountryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCountryService
        extends AbstractSimpleDimensionService<Country>
        implements CountryService {
    private CountryDao countryDao;

    @Autowired
    public DefaultCountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();
        countryDao.findAll().forEach(countries::add);
        logger.debug("Getting all Countries from DAO. Count of objects - {}"
                , countries.size());
        return countries;
    }

    @Override
    public int saveAll(List<Country> entities) {
        filterEntities(entities);
        countryDao.saveAll(entities);
        return entities.size();
    }
}
