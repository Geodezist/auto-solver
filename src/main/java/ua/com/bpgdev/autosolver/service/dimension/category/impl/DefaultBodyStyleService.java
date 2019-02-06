package ua.com.bpgdev.autosolver.service.dimension.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.autosolver.dao.jdbc.dimension.category.BodyStyleDao;
import ua.com.bpgdev.autosolver.entity.dimension.category.BodyStyle;
import ua.com.bpgdev.autosolver.service.dimension.category.BodyStyleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBodyStyleService
        extends AbstractDimensionWithCategoryService<BodyStyle>
        implements BodyStyleService {
    private BodyStyleDao bodyStyleDao;

    @Autowired
    public DefaultBodyStyleService(BodyStyleDao bodyStyleDao) {
        this.bodyStyleDao = bodyStyleDao;
    }

    @Override
    public List<BodyStyle> getAll() {
        List<BodyStyle> bodyStyles = new ArrayList<>();
        bodyStyleDao.findAll().forEach(bodyStyles::add);
        return bodyStyles;
    }

    @Override
    public List<BodyStyle> getByCategoryId(Long categoryId) {
        return bodyStyleDao.findByCategoryId(categoryId);
    }

    @Override
    public List<BodyStyle> getByCategoryValue(int categoryValue) {
        return bodyStyleDao.findByCategoryValue(categoryValue);
    }

    @Override
    public int saveAll(List<BodyStyle> entities) {
        entities.removeAll(getAll());
        bodyStyleDao.saveAll(entities);
        return entities.size();
    }
}
