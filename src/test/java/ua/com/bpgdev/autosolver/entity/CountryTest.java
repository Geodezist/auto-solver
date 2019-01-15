package ua.com.bpgdev.autosolver.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountryTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Country.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        Country expectedCountry = new Country( "TestCountry", 101);
        assertNotEquals(expectedId, expectedCountry.getId());
        expectedCountry.setId(expectedId);
        assertEquals(expectedId, expectedCountry.getId());
    }
}