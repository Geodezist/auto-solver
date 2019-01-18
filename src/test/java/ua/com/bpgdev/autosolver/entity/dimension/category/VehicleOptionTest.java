package ua.com.bpgdev.autosolver.entity.dimension.category;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.VehicleOption;

import static org.junit.Assert.*;

public class VehicleOptionTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(VehicleOption.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        VehicleOption expectedVehicleOption = new VehicleOption(new Category(), "TestVehicleOption", 101);
        assertNotEquals(expectedId, expectedVehicleOption.getId());
        expectedVehicleOption.setId(expectedId);
        assertEquals(expectedId, expectedVehicleOption.getId());
    }
}