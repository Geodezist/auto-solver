package ua.com.bpgdev.autosolver.entity.dimension.simple;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import ua.com.bpgdev.autosolver.entity.dimension.simple.VehicleColor;

import static org.junit.Assert.*;

public class VehicleColorTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(VehicleColor.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        VehicleColor expectedVehicleColor = new VehicleColor( "TestVehicleColor", 101);
        assertNotEquals(expectedId, expectedVehicleColor.getId());
        expectedVehicleColor.setId(expectedId);
        assertEquals(expectedId, expectedVehicleColor.getId());
    }
}