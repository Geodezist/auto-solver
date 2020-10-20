package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.GearBoxDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.GearBox;
import ua.com.bpgdev.autosolver.service.dimension.category.GearBoxService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultGearBoxService
        extends AbstractDimensionWithCategoryService<GearBox>
        implements GearBoxService {
    private final GearBoxDao gearBoxDao;

    @Override
    public List<GearBox> getAll() {
        List<GearBox> gearBoxes = new ArrayList<>();
        gearBoxDao.findAll(SORT_BY_VALUE_ASC).forEach(gearBoxes::add);
        log.debug("Getting all GearBoxes from DAO. Count of objects - {}"
                , gearBoxes.size());
        return gearBoxes;
    }

    @Override
    public List<GearBox> getByCategoryId(Long categoryId) {
        List<GearBox> result = gearBoxDao.findByCategoryId(categoryId, SORT_BY_VALUE_ASC);
        log.debug("Getting GearBoxes from DAO filtered by Category id = {}. Count of objects - {}"
                , categoryId
                , result.size());
        return result;
    }

    @Override
    public List<GearBox> getByCategoryValue(int categoryValue) {
        List<GearBox> result = gearBoxDao.findByCategoryValue(categoryValue, SORT_BY_VALUE_ASC);
        log.debug("Getting GearBoxes from DAO filtered by Category value = {}. Count of objects - {}"
                , categoryValue
                , result.size());
        return result;
    }

    @Override
    public int saveAll(List<GearBox> entities) {
        filterEntities(entities);
        gearBoxDao.saveAll(entities);
        return entities.size();
    }
}
