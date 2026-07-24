package com.interview.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exercise1_SetupTest {

    private final Calculator calculator = new Calculator();
    @Test
    public void myFirstTest() {
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
        System.out.println("Exercise 1 - PASSED: JUnit is set up and working!");
    }

    @Test
    public void testJUnitIsConfiguredCorrectly() {
        assertTrue(true, "JUnit 5 is correctly configured and running!");
        System.out.println("Exercise 1 - PASSED: JUnit configuration verified!");
    }
}
