package com.interview.junit;
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides a by b.
     * @throws IllegalArgumentException if b is zero
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero!");
        }
        return (double) a / b;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

   
    public Double safeSqrt(double value) {
        if (value < 0) return null;
        return Math.sqrt(value);
    }
}
