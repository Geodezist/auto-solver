package ua.com.bpgdev.autosolver.entity.dimension.category;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import ua.com.bpgdev.autosolver.entity.dimension.category.Category;
import ua.com.bpgdev.autosolver.entity.dimension.category.DimensionWithCategory;

import static org.junit.Assert.*;

public class DimensionWithCategoryTest {
    private Category expectedCategory;
    private String expectedName;
    private int expectedValue;

    private DimensionWithCategory expectedDimensionWithCategory;


    @Before
    public void beforeTest() {
        expectedCategory = new Category(1L, "TestCategory", 1);
        expectedName = "TestDimensionWithCategory";
        expectedValue = 1;

        expectedDimensionWithCategory = new DimensionWithCategory(
                expectedCategory,
                expectedName,
                expectedValue);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(DimensionWithCategory.class).verify();
    }

    @Test
    public void ConstructorAndSettersTest() {
        Category actualCategory = new Category(2L, "actualTestCategory", 2);
        String actualName = "actualTestDimensionWithCategory";
        int actualValue = 2;

        expectedDimensionWithCategory.setCategory(actualCategory);
        expectedDimensionWithCategory.setName(actualName);
        expectedDimensionWithCategory.setValue(actualValue);
        assertEquals(actualCategory, expectedDimensionWithCategory.getCategory());
        assertEquals(actualName, expectedDimensionWithCategory.getName());
        assertEquals(actualValue, expectedDimensionWithCategory.getValue());
    }

    @Test(expected = NullPointerException.class)
    public void setCategoryWithNullTest() {
        expectedDimensionWithCategory.setCategory(null);
    }

    @Test(expected = NullPointerException.class)
    public void setNameWithNullTest() {
        expectedDimensionWithCategory.setName(null);
    }

    @Test(expected = NullPointerException.class)
    public void allArgsConstructorWithCategoryIsNullTest() {
        expectedDimensionWithCategory = new DimensionWithCategory(null, expectedName, expectedValue);
    }

    @Test(expected = NullPointerException.class)
    public void allArgsConstructorWithNameIsNullTest() {
        expectedDimensionWithCategory = new DimensionWithCategory(expectedCategory, null, expectedValue);
    }
}