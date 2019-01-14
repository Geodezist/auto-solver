package ua.com.bpgdev.autosolver.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BodyStyleTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(BodyStyle.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        BodyStyle expectedBodyStyle = new BodyStyle(new Category(), "TestBodyStyle", 101);
        assertNotEquals(expectedId, expectedBodyStyle.getId());
        expectedBodyStyle.setId(expectedId);
        assertEquals(expectedId, expectedBodyStyle.getId());
    }
}