package ua.com.bpgdev.autosolver.entity.dimension.category;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CategoryTest {
    private Long actualId;
    private String actualName;
    private int actualValue;

    private Long expectedId;
    private String expectedName;
    private int expectedValue;

    private Category actualCategory;
    private Category expectedCategory;


    public CategoryTest(String actualId, String actualName, String actualValue, String expectedId, String expectedName, String expectedValue) {
        this.actualId = Long.valueOf(actualId);
        this.actualName = actualName;
        this.actualValue = Integer.valueOf(actualValue);

        this.expectedId = Long.valueOf(expectedId);
        this.expectedName = expectedName;
        this.expectedValue = Integer.valueOf(expectedValue);
    }

    @Parameterized.Parameters
    public static Collection<String[]> categoryObjects() {
        return Arrays.asList(new String[][]{
                {"1", "TestOne", "100", "1", "TestOne", "100"},
                {"2", "TestTwo", "200", "2", "TestTwo", "200"},
                {"3", "TestThree", "300", "3", "TestThree", "300"}
        });
    }

    @Before
    public void beforeTest() {
        actualCategory = new Category(actualId, actualName, actualValue);
        expectedCategory = new Category(expectedId, expectedName, expectedValue);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Category.class)
                .withRedefinedSuperclass()
                .withIgnoredAnnotations(javax.persistence.Id.class)
                .withIgnoredFields("id")
                .verify();
    }

    @Test
    public void setIdTest() {
        Long expectedId = 101L;
        assertNotEquals(expectedId, actualCategory.getId());
        actualCategory.setId(expectedId);
        assertEquals(expectedId, actualCategory.getId());
    }

    @Test
    public void setNameTest() {
        String expectedName = "NewTestName";
        assertNotEquals(expectedName, actualCategory.getName());
        actualCategory.setName(expectedName);
        assertEquals(expectedName, actualCategory.getName());
    }

    @Test(expected = NullPointerException.class)
    public void setNameWithNullTest() {
        actualCategory.setName(null);
    }

    @Test
    public void setValueTest() {
        int expectedValue = 999;
        assertNotEquals(expectedValue, actualCategory.getValue());
        actualCategory.setValue(expectedValue);
        assertEquals(expectedValue, actualCategory.getValue());
    }

    @Test
    public void toStringTest() {
        assertEquals(expectedCategory.toString(), actualCategory.toString());
    }

    @Test
    public void getIdTest() {
        assertEquals(expectedCategory.getId(), actualCategory.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals(expectedCategory.getName(), actualCategory.getName());
    }

    @Test
    public void getValueTest() {
        assertEquals(expectedCategory.getValue(), actualCategory.getValue());
    }

    @Test(expected = NullPointerException.class)
    public void CategoryAllArgsTest() {
        new Category(null, null, expectedValue);
    }

    @Test(expected = NullPointerException.class)
    public void CategoryRequiredArgsTest() {
        new Category(null, expectedValue);
    }
}