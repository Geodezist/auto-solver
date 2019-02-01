package ua.com.bpgdev.autosolver.populator.dimension.category;

import ua.com.bpgdev.autosolver.entity.dimension.category.DriveType;
import ua.com.bpgdev.autosolver.populator.Populator;

public interface DriveTypePopulator extends Populator {
    boolean populateDriveType(DriveType driveType);
}
