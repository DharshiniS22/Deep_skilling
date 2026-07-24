package com.interview.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Exercise 3: Assertions in JUnit")
public class Exercise3_AssertionsTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("assertEquals / assertNotEquals")
    public void testAssertions() {
        assertEquals(5, 2 + 3);
        assertEquals(10, calculator.add(4, 6));

        assertTrue(5 > 3);
        assertTrue(calculator.isEven(8));

        assertFalse(5 < 3);
        assertFalse(calculator.isEven(9));

        
        assertNull(null);
        assertNull(calculator.safeSqrt(-1));   
        
        assertNotNull(new Object());
        assertNotNull(calculator.safeSqrt(9)); 

        System.out.println("Exercise 3 - PASSED: all basic assertions");
    }

    @Test
    @DisplayName("assertThrows: verify exception is thrown")
    public void testAssertThrows() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.divide(10, 0)
        );
        assertTrue(ex.getMessage().contains("zero"));
        System.out.println("Exercise 3 - PASSED: assertThrows caught: " + ex.getMessage());
    }

    @Test
    @DisplayName("assertAll: all assertions run even if some fail")
    public void testAssertAll() {
      
        assertAll("All calculator operations",
            () -> assertEquals(5,  calculator.add(2, 3),       "add failed"),
            () -> assertEquals(2,  calculator.subtract(5, 3),  "subtract failed"),
            () -> assertEquals(12, calculator.multiply(3, 4),  "multiply failed"),
            () -> assertEquals(2.5, calculator.divide(5, 2),   "divide failed"),
            () -> assertTrue(calculator.isEven(10),            "isEven failed"),
            () -> assertEquals(9,  calculator.max(4, 9),       "max failed")
        );
        System.out.println("Exercise 3 - PASSED: assertAll with 6 grouped assertions");
    }

    @Test
    @DisplayName("assertArrayEquals: compare arrays element by element")
    public void testAssertArrayEquals() {
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual   = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, actual);
        System.out.println("Exercise 3 - PASSED: assertArrayEquals");
    }

    @Test
    @DisplayName("assertNotEquals: values are different")
    public void testAssertNotEquals() {
        assertNotEquals(0, calculator.add(1, 1));
        assertNotEquals("hello", "world");
        System.out.println("Exercise 3 - PASSED: assertNotEquals");
    }
}
