package ua.com.bpgdev.autosolver.populator.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.BodyStyle;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface BodyStylePopulator extends Populator {
    boolean populateBodyStyle(BodyStyle bodyStyle);
}
