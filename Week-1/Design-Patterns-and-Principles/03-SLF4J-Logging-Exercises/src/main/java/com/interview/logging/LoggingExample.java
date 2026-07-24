package com.interview.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ============================================================
 *  EXERCISE 1 — Logging Error Messages and Warning Levels
 * ============================================================
 *  CONCEPT: What is SLF4J?
 *  SLF4J (Simple Logging Facade for Java) is a logging FACADE
 *  (interface layer) that sits in front of logging frameworks
 *  like Logback, Log4j2, or java.util.logging.
 *
 *  Think of it like JDBC:
 *   JDBC API → your code       | SLF4J API  → your code
 *   JDBC Driver → MySQL driver | Logback    → implementation
 *
 *  LOG LEVELS (low → high severity):
 *   TRACE → finest detail (method entry/exit, loop counters)
 *   DEBUG → developer diagnostics (variable states)
 *   INFO  → normal operations (app started, record saved)
 *   WARN  → potential problem (app still works)
 *   ERROR → serious failure (operation failed)
 *
 *  Interview Tip: Use INFO/WARN in production.
 *  DEBUG/TRACE only during development or troubleshooting.
 * ============================================================
 */
public class LoggingExample {

    // ✅ Best Practice: static final — one Logger per class
    // LoggerFactory.getLogger(ClassName.class) ties the logger
    // to this class so log output is easy to trace.
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {

        System.out.println("=== SLF4J Logging Demo ===\n");

        // --------------------------------------------------------
        // TRACE — Most verbose. Usually disabled in production.
        // --------------------------------------------------------
        logger.trace("TRACE: Entering main() with {} argument(s)", args.length);

        // --------------------------------------------------------
        // DEBUG — For developers during development.
        // --------------------------------------------------------
        logger.debug("DEBUG: Application is initializing...");

        // --------------------------------------------------------
        // INFO — Normal operational events. Safe for production.
        // --------------------------------------------------------
        logger.info("INFO: Application started successfully on port {}", 8080);

        // --------------------------------------------------------
        // WARN — Something unexpected, but app can still continue.
        // --------------------------------------------------------
        logger.warn("WARN: This is a warning message — check your config");
        logger.warn("WARN: Response time {}ms exceeds threshold of {}ms", 1500, 1000);

        // --------------------------------------------------------
        // ERROR — A serious failure occurred.
        // --------------------------------------------------------
        logger.error("ERROR: This is an error message — an operation failed");

        // ✅ Best Practice: Log exceptions with full stack trace.
        //    Pass the exception as the LAST argument (no {} for it).
        try {
            riskyOperation();
        } catch (Exception e) {
            logger.error("ERROR: riskyOperation() failed: {}", e.getMessage(), e);
        }

        System.out.println("\n=== Demo Complete — check console output above ===");
    }

    /** Simulates a method that throws a RuntimeException. */
    private static void riskyOperation() {
        throw new RuntimeException("Simulated database connection failure");
    }
}
