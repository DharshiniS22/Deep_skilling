package com.interview.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * ============================================================
 *  MOCKITO EXERCISE 1: Mocking and Stubbing
 * ============================================================
 *  CONCEPT: What is Mockito?
 *  Mockito is a Java mocking framework. It lets you:
 *   1. Create MOCK objects (fake implementations of interfaces)
 *   2. STUB methods (define what a mock returns when called)
 *   3. VERIFY interactions (assert specific methods were called)
 *
 *  Key Vocabulary:
 *   MOCK  → Fake object; all methods return 0 / null / false by default
 *   STUB  → Configuring a mock: when(mock.method()).thenReturn(value)
 *   SPY   → Real object that also records calls (partial mock)
 *
 *  When to Mock?
 *   - External REST APIs
 *   - Databases / Repositories
 *   - File System / Network
 *   - Anything slow, flaky, or hard to set up in tests
 *
 *  Interview Tip:
 *   Q: Mock vs Stub vs Spy?
 *   A: Stub = return values only.
 *      Mock  = return values + verify interactions.
 *      Spy   = real object wrapped with interaction tracking.
 *
 *   Q: What does when(...).thenReturn(...) do?
 *   A: It stubs a method — when called on the mock, return
 *      the specified value instead of the real implementation.
 * ============================================================
 */
@DisplayName("Mockito Exercise 1: Mocking and Stubbing")
public class Exercise1_MockingAndStubbingTest {

    /**
     * SOLUTION — Exact match from the exercise requirement.
     * Steps:
     *  1. Create a mock for ExternalApi
     *  2. Stub getData() to return "Mock Data"
     *  3. Inject mock into MyService and call fetchData()
     *  4. Assert the result
     */
    @Test
    @DisplayName("testExternalApi: mock getData and verify result")
    public void testExternalApi() {
        // Step 1: Create mock
        ExternalApi mockApi = mock(ExternalApi.class);

        // Step 2: Stub the method
        when(mockApi.getData()).thenReturn("Mock Data");

        // Step 3: Use mock in the service
        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        // Step 4: Assert
        assertEquals("Mock Data", result);
        System.out.println("Exercise 1 - PASSED: testExternalApi, result = " + result);
    }

    /**
     * Demonstrates stubbing multiple methods on the same mock.
     */
    @Test
    @DisplayName("stubMultipleMethods: stub getData and sendData")
    public void testStubMultipleMethods() {
        // ARRANGE
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Order #12345");
        when(mockApi.sendData("valid")).thenReturn(true);
        when(mockApi.sendData("invalid")).thenReturn(false);

        MyService service = new MyService(mockApi);

        // ACT
        String fetchResult    = service.fetchData();
        String publishSuccess = service.publishData("valid");
        String publishFailure = service.publishData("invalid");

        // ASSERT
        assertEquals("Order #12345",           fetchResult);
        assertEquals("Published successfully",  publishSuccess);
        assertEquals("Publish failed",          publishFailure);

        System.out.println("Exercise 1 - PASSED: stub multiple methods");
    }

    /**
     * Demonstrates stubbing with specific argument values.
     * Un-stubbed calls return null (Mockito default for objects).
     */
    @Test
    @DisplayName("stubGetDataById: stub with specific argument")
    public void testStubGetDataById() {
        // ARRANGE
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getDataById(1)).thenReturn("User: Alice");
        when(mockApi.getDataById(2)).thenReturn("User: Bob");
        // getDataById(99) NOT stubbed → returns null → service returns "NOT_FOUND"

        MyService service = new MyService(mockApi);

        // ACT + ASSERT
        assertEquals("User: Alice", service.fetchDataById(1));
        assertEquals("User: Bob",   service.fetchDataById(2));
        assertEquals("NOT_FOUND",   service.fetchDataById(99));

        System.out.println("Exercise 1 - PASSED: stub with specific arguments");
    }

    /**
     * Demonstrates thenThrow() — stub a method to THROW an exception.
     * Use this to test your error-handling / catch blocks.
     */
    @Test
    @DisplayName("thenThrow: stub a method to throw an exception")
    public void testStubThrowException() {
        // ARRANGE
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenThrow(new RuntimeException("API is down!"));

        MyService service = new MyService(mockApi);

        // ACT + ASSERT
        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> service.fetchData()
        );
        assertEquals("API is down!", ex.getMessage());
        System.out.println("Exercise 1 - PASSED: thenThrow, caught: " + ex.getMessage());
    }
}
