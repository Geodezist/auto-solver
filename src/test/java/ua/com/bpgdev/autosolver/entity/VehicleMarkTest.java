package ua.com.bpgdev.autosolver.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleMarkTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(VehicleMark.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        VehicleMark expectedVehicleMark = new VehicleMark(new Category(), "TestGearBox", 101);
        assertNotEquals(expectedId, expectedVehicleMark.getId());
        expectedVehicleMark.setId(expectedId);
        assertEquals(expectedId, expectedVehicleMark.getId());
    }
}