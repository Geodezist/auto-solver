package ua.com.bpgdev.autosolver.entity.dimension.category;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleMark;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleModel;

import static org.junit.Assert.*;

public class VehicleModelTest {

    private Long expectedId;
    private String expectedName;
    private int expectedValue;
    private VehicleMark expectedVehicleMark;

    private VehicleModel actualVehicleModel;
    private VehicleModel expectedVehicleModel;

    @Before
    public void beforeTest() {
        String actualName = "actualTestVehicleModel";
        int actualValue = 101;
        Category actualCategory = new Category(10L, "actualTestCategory", 110);
        VehicleMark actualVehicleMark = new VehicleMark(100L, actualCategory, "actualTestVehicleMark", 1100);

        actualVehicleModel = new VehicleModel(actualVehicleMark, actualName, actualValue);

        expectedId = 2L;
        expectedName = "expectedTestVehicleModel";
        expectedValue = 201;
        Category expectedCategory = new Category(20L, "expectedTestCategory", 220);
        expectedVehicleMark = new VehicleMark(200L, expectedCategory, "expectedTestVehicleMark", 2200);

        expectedVehicleModel = new VehicleModel(expectedVehicleMark, expectedName, expectedValue);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(VehicleModel.class)
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .withIgnoredFields("id")
                .verify();
    }

    @Test
    public void toStringTest() {
        actualVehicleModel = new VehicleModel(expectedVehicleMark, expectedName, expectedValue);
        assertEquals(expectedVehicleModel.toString(), actualVehicleModel.toString());
    }

    @Test
    public void setIdTest() {
        assertNotEquals(expectedId, actualVehicleModel.getId());
        actualVehicleModel.setId(expectedId);
        assertEquals(expectedId, actualVehicleModel.getId());
    }

    @Test
    public void setVehicleMarkTest() {
        assertNotEquals(expectedVehicleModel.getVehicleMark(), actualVehicleModel.getVehicleMark());
        actualVehicleModel.setVehicleMark(expectedVehicleMark);
        assertEquals(expectedVehicleModel.getVehicleMark(), actualVehicleModel.getVehicleMark());
    }

    @Test(expected = NullPointerException.class)
    public void setVehicleMarkWithNullTest() {
        actualVehicleModel.setVehicleMark(null);
    }

    @Test
    public void setNameTest() {
        assertNotEquals(expectedVehicleModel.getName(), actualVehicleModel.getName());
        actualVehicleModel.setName(expectedName);
        assertEquals(expectedVehicleModel.getName(), actualVehicleModel.getName());
    }

    @Test(expected = NullPointerException.class)
    public void setNameWithNullTest() {
        actualVehicleModel.setName(null);
    }

    @Test
    public void setValueTest() {
        assertNotEquals(expectedVehicleModel.getValue(), actualVehicleModel.getValue());
        actualVehicleModel.setValue(expectedValue);
        assertEquals(expectedVehicleModel.getValue(), actualVehicleModel.getValue());
    }

    @Test(expected = NullPointerException.class)
    public void requiredArdConstructorWithMarkNullTest() {
        new VehicleModel(null, expectedName, expectedValue);
    }

    @Test(expected = NullPointerException.class)
    public void requiredArdConstructorWithNameNullTest() {
        new VehicleModel(expectedVehicleMark, null, expectedValue);
    }
}