package com.interview.mockito;

/**
 * ============================================================
 *  ExternalApi — Interface representing an external dependency
 * ============================================================
 *  In real applications, services call external systems
 *  (REST APIs, databases, message queues).
 *
 *  Why use an INTERFACE here?
 *   - Easy to mock with Mockito.mock(ExternalApi.class)
 *   - Follows Dependency Inversion Principle (DIP)
 *   - In tests  → pass a MOCK
 *   - In production → pass the real implementation
 * ============================================================
 */
public interface ExternalApi {

    /** Simulates fetching data from an external source. */
    String getData();

    /**
     * Simulates sending data to an external endpoint.
     * @return true if succeeded, false otherwise
     */
    boolean sendData(String payload);

    /**
     * Simulates fetching data by a given ID.
     * @return data string, or null if not found
     */
    String getDataById(int id);
}
