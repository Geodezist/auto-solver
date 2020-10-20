package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.BodyStyleDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.BodyStyle;
import ua.com.bpgdev.autosolver.service.dimension.category.BodyStyleService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultBodyStyleService extends AbstractDimensionWithCategoryService<BodyStyle> implements BodyStyleService {
    private final BodyStyleDao bodyStyleDao;

    @Override
    public List<BodyStyle> getAll() {
        List<BodyStyle> bodyStyles = new ArrayList<>();
        bodyStyleDao.findAll(SORT_BY_VALUE_ASC).forEach(bodyStyles::add);
        log.debug("Getting all BodyStyles from DAO. Count of objects - {}"
                , bodyStyles.size());
        return bodyStyles;
    }

    @Override
    public List<BodyStyle> getByCategoryId(Long categoryId) {
        List<BodyStyle> result = bodyStyleDao.findByCategoryId(categoryId, SORT_BY_VALUE_ASC);
        log.debug("Getting BodyStyles from DAO filtered by Category id = {}. Count of objects - {}"
                , categoryId
                , result.size());
        return result;
    }

    @Override
    public List<BodyStyle> getByCategoryValue(int categoryValue) {
        List<BodyStyle> result = bodyStyleDao.findByCategoryValue(categoryValue, SORT_BY_VALUE_ASC);
        log.debug("Getting BodyStyles from DAO filtered by Category value = {}. Count of objects - {}"
                , categoryValue
                , result.size());
        return result;
    }

    @Override
    public int saveAll(List<BodyStyle> entities) {
        filterEntities(entities);
        bodyStyleDao.saveAll(entities);
        return entities.size();
    }
}
