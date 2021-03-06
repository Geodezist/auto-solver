package ua.com.bpgdev.autosolver.entity.dimension.simple;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import ua.com.bpgdev.autosolver.entity.dimension.simple.UkraineState;

import static org.junit.Assert.*;

public class UkraineStateTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(UkraineState.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .withIgnoredFields("id")
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        UkraineState expectedUkraineState = new UkraineState("TestUkraineState", 101);
        assertNotEquals(expectedId, expectedUkraineState.getId());
        expectedUkraineState.setId(expectedId);
        assertEquals(expectedId, expectedUkraineState.getId());
    }
}