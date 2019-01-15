package ua.com.bpgdev.autosolver.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleDimensionTest {
    private int expectedValue;
    private SimpleDimension expectedSimpleDimension;

    @Before
    public void beforeTest() {
        String expectedName = "TestDimensionWithCategory";
        expectedValue = 1;

        expectedSimpleDimension = new SimpleDimension(expectedName, expectedValue);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(SimpleDimension.class).verify();
    }

    @Test
    public void ConstructorAndSettersTest() {
        String actualName = "actualTestSimpleDimension";
        int actualValue = 2;

        expectedSimpleDimension.setName(actualName);
        expectedSimpleDimension.setValue(actualValue);
        assertEquals(actualName, expectedSimpleDimension.getName());
        assertEquals(actualValue, expectedSimpleDimension.getValue());
    }

    @Test(expected = NullPointerException.class)
    public void setNameWithNullTest() {
        expectedSimpleDimension.setName(null);
    }

    @Test(expected = NullPointerException.class)
    public void allArgsConstructorWithNameIsNullTest() {
        expectedSimpleDimension = new SimpleDimension(null, expectedValue);
    }
}