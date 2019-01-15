package ua.com.bpgdev.autosolver.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.*;

public class FuelTypeTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(FuelType.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        FuelType expectedFuelType = new FuelType( "TestFuelType", 101);
        assertNotEquals(expectedId, expectedFuelType.getId());
        expectedFuelType.setId(expectedId);
        assertEquals(expectedId, expectedFuelType.getId());
    }
}