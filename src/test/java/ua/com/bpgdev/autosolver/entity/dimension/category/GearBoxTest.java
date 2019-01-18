package ua.com.bpgdev.autosolver.entity.dimension.category;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.GearBox;

import static org.junit.Assert.*;

public class GearBoxTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(GearBox.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        GearBox expectedGearBox = new GearBox(new Category(), "TestGearBox", 101);
        assertNotEquals(expectedId, expectedGearBox.getId());
        expectedGearBox.setId(expectedId);
        assertEquals(expectedId, expectedGearBox.getId());
    }
}