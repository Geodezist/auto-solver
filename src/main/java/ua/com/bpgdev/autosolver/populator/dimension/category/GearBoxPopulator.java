package ua.com.bpgdev.autosolver.populator.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.GearBox;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface GearBoxPopulator extends Populator {
    boolean populateGearBox(GearBox gearBox);
}
