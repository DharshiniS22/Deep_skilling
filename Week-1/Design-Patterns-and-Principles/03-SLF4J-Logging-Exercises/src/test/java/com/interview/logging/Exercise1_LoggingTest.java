package com.interview.logging;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ============================================================
 *  SLF4J EXERCISE 1: Logging Error Messages and Warning Levels
 * ============================================================
 *  CONCEPT: SLF4J Architecture
 *   Your Code → SLF4J API (facade) → Logback (implementation)
 *
 *  This follows Dependency Inversion Principle:
 *   - Code against the abstraction (SLF4J API).
 *   - Swap implementation (Logback → Log4j2) without code change.
 *
 *  LOG LEVELS (low → high severity):
 *   TRACE → finest tracing (usually disabled in production)
 *   DEBUG → developer diagnostics
 *   INFO  → normal business events
 *   WARN  → potential problems (app still works)
 *   ERROR → serious failures
 *
 *  Best Practices:
 *   1. Logger is static final — one per class
 *   2. Use {} placeholders — lazy evaluation (performance)
 *   3. Pass exceptions as LAST argument (no {} for them)
 *
 *  Interview Tip:
 *   Q: SLF4J vs Logback?
 *   A: SLF4J = API/facade (what you write against).
 *      Logback = implementation (does the actual writing).
 *
 *   Q: Why use {} instead of string concatenation?
 *   A: If DEBUG is disabled, the string is NEVER built.
 *      String concatenation always evaluates — wasteful!
 * ============================================================
 */
@DisplayName("SLF4J Exercise 1: Logging Levels Demonstration")
public class Exercise1_LoggingTest {

    // ✅ Best Practice: static final — one Logger per class
    private static final Logger logger = LoggerFactory.getLogger(Exercise1_LoggingTest.class);

    @Test
    @DisplayName("Logger should be created successfully")
    public void testLoggerCreation() {
        assertNotNull(logger, "Logger should not be null");
        System.out.println("PASSED: Logger created = " + logger.getName());
    }

    @Test
    @DisplayName("ERROR level: log error messages")
    public void testErrorLogging() {
        assertDoesNotThrow(() -> {
            // This is an error message (as required by the exercise)
            logger.error("This is an error message");
            logger.error("Failed to connect to database after {} retries", 3);
        });
        System.out.println("PASSED: ERROR level logging works");
    }

    @Test
    @DisplayName("WARN level: log warning messages")
    public void testWarnLogging() {
        assertDoesNotThrow(() -> {
            // This is a warning message (as required by the exercise)
            logger.warn("This is a warning message");
            logger.warn("Response time {}ms exceeds threshold of {}ms", 1500, 1000);
        });
        System.out.println("PASSED: WARN level logging works");
    }

    @Test
    @DisplayName("INFO level: log informational messages")
    public void testInfoLogging() {
        assertDoesNotThrow(() -> {
            logger.info("Application started successfully on port {}", 8080);
            logger.info("Processing request for user: {}", "alice@example.com");
        });
        System.out.println("PASSED: INFO level logging works");
    }

    @Test
    @DisplayName("DEBUG level: log debug messages")
    public void testDebugLogging() {
        assertDoesNotThrow(() -> {
            logger.debug("Entering method calculateTotal() with {} items", 5);
            logger.debug("Intermediate result = {}", 42);
        });
        System.out.println("PASSED: DEBUG level logging works");
    }

    @Test
    @DisplayName("TRACE level: log trace messages")
    public void testTraceLogging() {
        assertDoesNotThrow(() -> {
            logger.trace("Loop iteration {} of {}", 1, 10);
        });
        System.out.println("PASSED: TRACE level logging works");
    }

    @Test
    @DisplayName("Exception logging: log error with full stack trace")
    public void testExceptionLogging() {
        // ✅ Best Practice: pass the exception as the LAST argument
        //    SLF4J will print the full stack trace in the log output
        assertDoesNotThrow(() -> {
            try {
                String s = null;
                s.length();  // deliberate NullPointerException
            } catch (NullPointerException e) {
                logger.error("Null pointer encountered: {}", e.getMessage(), e);
            }
        });
        System.out.println("PASSED: Exception logging with stack trace works");
    }

    @Test
    @DisplayName("Parameterized logging: {} placeholder demo")
    public void testParameterizedLogging() {
        /*
         * ❌ BAD (always concatenates even if DEBUG is off):
         *    logger.debug("Value = " + expensiveMethod());
         *
         * ✅ GOOD (builds string ONLY if DEBUG is enabled):
         *    logger.debug("Value = {}", expensiveMethod());
         */
        assertDoesNotThrow(() -> {
            String user = "Bob";
            int    age  = 30;
            String city = "Bangalore";
            logger.info("User {} aged {} is from {}", user, age, city);
        });
        System.out.println("PASSED: Parameterized {} logging works");
    }

    @Test
    @DisplayName("All levels: demonstrate complete log hierarchy")
    public void testAllLevelsInOrder() {
        assertDoesNotThrow(() -> {
            logger.trace("TRACE — most verbose");
            logger.debug("DEBUG — development details");
            logger.info("INFO  — normal operations");
            logger.warn("WARN  — potential problem");
            logger.error("ERROR — serious failure");
        });
        System.out.println("PASSED: All 5 log levels executed successfully");
        System.out.println("        (See console output above for logged lines)");
    }
}
