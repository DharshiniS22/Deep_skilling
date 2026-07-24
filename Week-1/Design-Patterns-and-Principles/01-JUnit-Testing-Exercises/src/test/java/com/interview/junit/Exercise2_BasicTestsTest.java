package com.interview.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercise 2: Basic JUnit Tests for Calculator")
public class Exercise2_BasicTestsTest {

    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("add: two positive numbers returns correct sum")
    public void add_twoPositiveNumbers_returnsCorrectSum() {
        int result = calculator.add(10, 5);
        assertEquals(15, result);
        System.out.println("Exercise 2 - PASSED: add(10, 5) = " + result);
    }

    @Test
    @DisplayName("add: negative numbers returns correct sum")
    public void add_negativeNumbers_returnsCorrectSum() {
        assertEquals(-8, calculator.add(-3, -5));
        System.out.println("Exercise 2 - PASSED: add(-3, -5) = -8");
    }

    @Test
    @DisplayName("add: zero plus a number returns the same number")
    public void add_zeroAndNumber_returnsSameNumber() {
        assertEquals(7, calculator.add(0, 7));
        System.out.println("Exercise 2 - PASSED: add(0, 7) = 7");
    }

    @Test
    @DisplayName("subtract: larger minus smaller returns positive")
    public void subtract_largerMinusSmaller_returnsPositive() {
        assertEquals(5, calculator.subtract(10, 5));
        System.out.println("Exercise 2 - PASSED: subtract(10, 5) = 5");
    }

    @Test
    @DisplayName("subtract: same numbers returns zero")
    public void subtract_sameNumbers_returnsZero() {
        assertEquals(0, calculator.subtract(7, 7));
        System.out.println("Exercise 2 - PASSED: subtract(7, 7) = 0");
    }

    @Test
    @DisplayName("multiply: two numbers returns correct product")
    public void multiply_twoNumbers_returnsProduct() {
        assertEquals(20, calculator.multiply(4, 5));
        System.out.println("Exercise 2 - PASSED: multiply(4, 5) = 20");
    }

    @Test
    @DisplayName("multiply: any number with zero returns zero")
    public void multiply_withZero_returnsZero() {
        assertEquals(0, calculator.multiply(99, 0));
        System.out.println("Exercise 2 - PASSED: multiply(99, 0) = 0");
    }


    @Test
    @DisplayName("divide: valid numbers returns correct quotient")
    public void divide_validNumbers_returnsCorrectQuotient() {
        assertEquals(2.5, calculator.divide(5, 2), 0.001);
        System.out.println("Exercise 2 - PASSED: divide(5, 2) = 2.5");
    }

    @Test
    @DisplayName("divide: division by zero throws IllegalArgumentException")
    public void divide_byZero_throwsIllegalArgumentException() {
    
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.divide(10, 0),
            "Dividing by zero should throw IllegalArgumentException"
        );
        assertEquals("Cannot divide by zero!", exception.getMessage());
        System.out.println("Exercise 2 - PASSED: divide(10,0) threw: " + exception.getMessage());
    }

    @Test
    @DisplayName("isEven: even number returns true")
    public void isEven_evenNumber_returnsTrue() {
        assertTrue(calculator.isEven(4));
        System.out.println("Exercise 2 - PASSED: isEven(4) = true");
    }

    @Test
    @DisplayName("isEven: odd number returns false")
    public void isEven_oddNumber_returnsFalse() {
        assertFalse(calculator.isEven(7));
        System.out.println("Exercise 2 - PASSED: isEven(7) = false");
    }
}
