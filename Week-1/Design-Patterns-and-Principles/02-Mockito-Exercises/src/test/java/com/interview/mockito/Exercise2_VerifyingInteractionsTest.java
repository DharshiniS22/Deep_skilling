package com.interview.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * ============================================================
 *  MOCKITO EXERCISE 2: Verifying Interactions
 * ============================================================
 *  CONCEPT: Why Verify?
 *  Sometimes the behaviour you want to test is NOT a return
 *  value but WHETHER a specific method was CALLED.
 *
 *  Examples:
 *   - Did the service call repository.save()?
 *   - Was the email sender called exactly once?
 *   - Was an event published with the right payload?
 *   - Was a dangerous method NEVER called?
 *
 *  Verification methods:
 *   verify(mock).method()            → called exactly once
 *   verify(mock, times(3)).method()  → called exactly 3 times
 *   verify(mock, never()).method()   → NEVER called
 *   verify(mock, atLeast(2)).method()→ called >= 2 times
 *   verify(mock, atMost(5)).method() → called <= 5 times
 *   verifyNoMoreInteractions(mock)   → no unexpected calls
 *
 *  CONCEPT: Argument Matchers (ArgumentMatchers.*)
 *   any()       → any object
 *   anyString() → any String
 *   anyInt()    → any int
 *   eq("exact") → exact value
 *
 *  CONCEPT: Annotation-Based Mocks (@Mock + @InjectMocks)
 *   @Mock ExternalApi mockApi;     → Mockito creates the mock
 *   @InjectMocks MyService service;→ Mockito injects @Mock fields
 *
 *  Interview Tip:
 *   Q: Difference between stubbing and verifying?
 *   A: Stubbing = setup (what mock returns).
 *      Verifying = assertion (was it called?).
 *
 *   Q: What is @InjectMocks?
 *   A: Tells Mockito to create an instance of the class and
 *      inject all @Mock fields into it automatically.
 * ============================================================
 */
@ExtendWith(MockitoExtension.class)      // enables @Mock and @InjectMocks
@DisplayName("Mockito Exercise 2: Verifying Interactions")
public class Exercise2_VerifyingInteractionsTest {

    @Mock
    private ExternalApi mockApi;         // Mockito creates and manages this mock

    @InjectMocks
    private MyService service;           // Mockito injects mockApi into this

    // ---- Basic Verify ----

    /**
     * SOLUTION — Exact match from the exercise requirement.
     * Steps:
     *  1. Create mock (done via @Mock above)
     *  2. Call the method
     *  3. Verify the interaction
     */
    @Test
    @DisplayName("testVerifyInteraction: verify getData() was called once")
    public void testVerifyInteraction() {
        when(mockApi.getData()).thenReturn("Some Data");

        // ACT
        service.fetchData();

        // VERIFY — was getData() called exactly once?
        verify(mockApi).getData();   // same as verify(mockApi, times(1)).getData()
        System.out.println("Exercise 2 - PASSED: getData() was called exactly once");
    }

    // ---- Verify times(N) ----

    @Test
    @DisplayName("verify times(3): method called exactly 3 times")
    public void testVerifyTimes() {
        when(mockApi.getData()).thenReturn("data");

        service.fetchData();
        service.fetchData();
        service.fetchData();

        verify(mockApi, times(3)).getData();
        System.out.println("Exercise 2 - PASSED: getData() called 3 times");
    }

    // ---- Verify never() ----

    @Test
    @DisplayName("verify never(): method was NOT called")
    public void testVerifyNever() {
        // We intentionally don't call any service method
        verify(mockApi, never()).getData();
        System.out.println("Exercise 2 - PASSED: getData() was NEVER called");
    }

    // ---- Verify with Argument Matchers ----

    @Test
    @DisplayName("verify with exact argument")
    public void testVerifyWithExactArgument() {
        when(mockApi.sendData("Hello World")).thenReturn(true);
        service.publishData("Hello World");
        verify(mockApi).sendData("Hello World");
        System.out.println("Exercise 2 - PASSED: sendData() called with exact argument");
    }

    @Test
    @DisplayName("verify with anyString(): any String argument")
    public void testVerifyWithAnyString() {
        when(mockApi.sendData(anyString())).thenReturn(true);
        service.publishData("any payload");
        verify(mockApi).sendData(anyString());
        System.out.println("Exercise 2 - PASSED: sendData() called with anyString()");
    }

    @Test
    @DisplayName("verify with anyInt(): any int argument")
    public void testVerifyWithAnyInt() {
        when(mockApi.getDataById(anyInt())).thenReturn("data");
        service.fetchDataById(42);
        verify(mockApi).getDataById(anyInt());
        System.out.println("Exercise 2 - PASSED: getDataById() called with anyInt()");
    }

    // ---- Verify atLeast / atMost ----

    @Test
    @DisplayName("verify atLeast(2): called at least 2 times")
    public void testVerifyAtLeast() {
        when(mockApi.getData()).thenReturn("data");
        service.fetchData();
        service.fetchData();
        service.fetchData();                // 3 calls total
        verify(mockApi, atLeast(2)).getData();
        System.out.println("Exercise 2 - PASSED: getData() called at least 2 times");
    }

    @Test
    @DisplayName("verify atMost(5): called at most 5 times")
    public void testVerifyAtMost() {
        when(mockApi.getData()).thenReturn("data");
        service.fetchData();
        service.fetchData();                // 2 calls total
        verify(mockApi, atMost(5)).getData();
        System.out.println("Exercise 2 - PASSED: getData() called at most 5 times");
    }

    // ---- verifyNoMoreInteractions ----

    @Test
    @DisplayName("verifyNoMoreInteractions: no unexpected calls")
    public void testVerifyNoMoreInteractions() {
        when(mockApi.getData()).thenReturn("data");
        service.fetchData();
        verify(mockApi).getData();
        verifyNoMoreInteractions(mockApi);  // fails if any OTHER method was called
        System.out.println("Exercise 2 - PASSED: no unexpected interactions");
    }
}
