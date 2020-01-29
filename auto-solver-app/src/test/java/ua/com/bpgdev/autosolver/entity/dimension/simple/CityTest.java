package ua.com.bpgdev.autosolver.entity.dimension.simple;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import ua.com.bpgdev.autosolver.entity.dimension.simple.City;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;

import static org.junit.Assert.*;

public class CityTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(City.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .withIgnoredFields("id")
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        City expectedCity = new City(new UkraineState(), "TestCity", 101);
        assertNotEquals(expectedId, expectedCity.getId());
        expectedCity.setId(expectedId);
        assertEquals(expectedId, expectedCity.getId());
    }

    @Test
    public void setUkraineStateTest() {
        UkraineState expectedUkraineState = new UkraineState("Киевская", 1);
        City expectedCity = new City(new UkraineState(), "TestCity", 101);
        assertNotEquals(expectedUkraineState, expectedCity.getUkraineState());
        expectedCity.setUkraineState(expectedUkraineState);
        assertEquals(expectedUkraineState, expectedCity.getUkraineState());
    }

    @Test(expected = NullPointerException.class)
    public void setUkraineStateNullTest() {
        City expectedCity = new City(new UkraineState(), "TestCity", 101);
        expectedCity.setUkraineState(null);
    }
}