package ua.com.bpgdev.autosolver.service.dimension.simple.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.simple.CityDao;
import ua.com.bpgdev.autosolver.entity.dimension.simple.City;
import ua.com.bpgdev.autosolver.service.dimension.simple.CityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCityService implements CityService {
    private CityDao cityDao;

    @Autowired
    public DefaultCityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        cityDao.findAll().forEach(cities::add);
        return cities;
    }

    @Override
    public int saveAll(List<City> entities) {
        entities.removeAll(getAll());
        cityDao.saveAll(entities);
        return entities.size();
    }

    @Override
    public boolean save(City entity) {
        if (getAll().indexOf(entity) == -1) {
            cityDao.save(entity);
            return true;
        }
        return false;
    }
}
