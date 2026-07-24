# SLF4J Logging Exercises

Interview preparation exercises for SLF4J + Logback.

## Project Structure
```
03-SLF4J-Logging-Exercises/
├── pom.xml
├── src/main/java/com/interview/logging/
│   └── LoggingExample.java              ← Main demo: all log levels
├── src/main/resources/
│   └── logback.xml                      ← Logback config (console + file)
└── src/test/java/com/interview/logging/
    └── Exercise1_LoggingTest.java       ← Log level tests
└── src/test/resources/
    └── logback-test.xml                 ← Logback config for tests
```

## How to Run Tests
```bash
mvn test
```

## How to Run the Demo App
```bash
mvn compile exec:java -Dexec.mainClass="com.interview.logging.LoggingExample"
```

## Exercises Covered

| Exercise | Topic | Key Concepts |
|---|---|---|
| Exercise 1 | All Log Levels | TRACE, DEBUG, INFO, WARN, ERROR, parameterized `{}` |

## Key Concepts

### SLF4J Architecture
```
Your Code  →  SLF4J API (facade)  →  Logback (implementation)
```
- **SLF4J** = the API you write against (interface)
- **Logback** = the actual implementation (writes to console/file)
- You can swap Logback for Log4j2 without changing any code

### Log Levels (Low → High)
| Level | Use Case |
|---|---|
| `TRACE` | Finest detail — loop counters, method entry/exit |
| `DEBUG` | Developer diagnostics — variable values |
| `INFO` | Business events — user login, order placed |
| `WARN` | Potential problem — app still runs |
| `ERROR` | Serious failure — operation failed |

### Usage
```java
// Create logger (one per class, static final)
private static final Logger logger = LoggerFactory.getLogger(MyClass.class);

// Log with {} placeholders (lazy evaluation = better performance)
logger.error("This is an error message");
logger.warn("This is a warning message");
logger.info("User {} logged in", username);
logger.debug("Value = {}", value);

// Log exception with stack trace (exception is LAST argument, no {} for it)
logger.error("Operation failed: {}", e.getMessage(), e);
```

### Maven Dependencies
```xml
<!-- SLF4J API -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.9</version>
</dependency>

<!-- Logback implementation -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.11</version>
</dependency>
```
