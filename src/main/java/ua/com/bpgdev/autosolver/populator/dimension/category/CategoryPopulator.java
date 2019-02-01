package ua.com.bpgdev.autosolver.populator.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface CategoryPopulator extends Populator {
    boolean populateCategory(Category category);
}
