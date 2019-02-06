package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.GearBoxDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.GearBox;
import ua.com.bpgdev.autosolver.service.dimension.category.GearBoxService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultGearBoxService
        extends AbstractDimensionWithCategoryService<GearBox>
        implements GearBoxService {
    private GearBoxDao gearBoxDao;

    @Autowired
    public DefaultGearBoxService(GearBoxDao gearBoxDao) {
        this.gearBoxDao = gearBoxDao;
    }

    @Override
    public List<GearBox> getAll() {
        List<GearBox> gearBoxes = new ArrayList<>();
        gearBoxDao.findAll().forEach(gearBoxes::add);
        return gearBoxes;
    }

    @Override
    public List<GearBox> getByCategoryId(Long categoryId) {
        return gearBoxDao.findByCategoryId(categoryId);
    }

    @Override
    public List<GearBox> getByCategoryValue(int categoryValue) {
        return gearBoxDao.findByCategoryValue(categoryValue);
    }

    @Override
    public int saveAll(List<GearBox> entities) {
        entities.removeAll(getAll());
        gearBoxDao.saveAll(entities);
        return entities.size();
    }
}
