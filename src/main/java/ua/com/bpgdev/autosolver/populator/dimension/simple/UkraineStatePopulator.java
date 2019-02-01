package ua.com.bpgdev.autosolver.populator.dimension.simple;

import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface UkraineStatePopulator extends Populator {
    boolean populateUkraineState(UkraineState ukraineState);
}
