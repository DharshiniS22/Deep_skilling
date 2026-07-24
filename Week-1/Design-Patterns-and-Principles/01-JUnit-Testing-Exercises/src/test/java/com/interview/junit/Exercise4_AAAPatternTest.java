package com.interview.junit;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercise 4: AAA Pattern + Setup & Teardown")
public class Exercise4_AAAPatternTest {

    private Calculator calculator;

    @BeforeAll
    static void globalSetup() {
        System.out.println("\n@BeforeAll  — Test Suite Starting");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();   
        System.out.println("  @BeforeEach — fresh Calculator created");
    }

    @AfterEach
    void tearDown() {
        System.out.println("  @AfterEach  — cleanup complete");
    }

    @AfterAll
    static void globalTearDown() {
        System.out.println("@AfterAll   — Test Suite Complete\n");
    }

    @Test
    @DisplayName("add: AAA — add two positive numbers")
    public void add_twoPositiveNumbers_AAA() {
   
        int firstNumber  = 10;
        int secondNumber = 20;
        int expected     = 30;

        int actual = calculator.add(firstNumber, secondNumber);

 
        assertEquals(expected, actual);
        System.out.println("  PASSED: add(" + firstNumber + ", " + secondNumber + ") = " + actual);
    }

    @Test
    @DisplayName("divide: AAA — valid division")
    public void divide_validInputs_AAA() {
   
        int dividend  = 20;
        int divisor   = 4;
        double expected = 5.0;

        double actual = calculator.divide(dividend, divisor);

        assertEquals(expected, actual, 0.001);
        System.out.println("  PASSED: divide(" + dividend + ", " + divisor + ") = " + actual);
    }

    @Test
    @DisplayName("divide: AAA — division by zero throws exception")
    public void divide_byZero_throwsException_AAA() {
        int dividend = 15;
        int divisor  = 0;

        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.divide(dividend, divisor)
        );
        assertTrue(ex.getMessage().contains("zero"));
        System.out.println("  PASSED: divide-by-zero caught: " + ex.getMessage());
    }

    @Test
    @DisplayName("multiply: AAA — positive by negative")
    public void multiply_positiveByNegative_AAA() {
        int a        = 5;
        int b        = -4;
        int expected = -20;

        int actual = calculator.multiply(a, b);

        assertEquals(expected, actual);
        System.out.println("  PASSED: multiply(" + a + ", " + b + ") = " + actual);
    }

    @Test
    @DisplayName("max: AAA — returns the larger of two numbers")
    public void max_twoNumbers_returnsLarger_AAA() {
        // ARRANGE
        int a        = 42;
        int b        = 17;
        int expected = 42;

        // ACT
        int actual = calculator.max(a, b);

        // ASSERT
        assertEquals(expected, actual);
        System.out.println("  PASSED: max(" + a + ", " + b + ") = " + actual);
    }
}
