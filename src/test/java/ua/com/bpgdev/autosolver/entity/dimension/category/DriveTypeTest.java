package ua.com.bpgdev.autosolver.entity.dimension.category;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.DriveType;

import static org.junit.Assert.*;

public class DriveTypeTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(DriveType.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .withIgnoredFields("id")
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        DriveType expectedDriveType = new DriveType(new Category(), "TestDriveType", 101);
        assertNotEquals(expectedId, expectedDriveType.getId());
        expectedDriveType.setId(expectedId);
        assertEquals(expectedId, expectedDriveType.getId());
    }
}