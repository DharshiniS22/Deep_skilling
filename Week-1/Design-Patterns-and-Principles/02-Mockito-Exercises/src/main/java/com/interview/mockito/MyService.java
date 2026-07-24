package com.interview.mockito;

/**
 * ============================================================
 *  MyService — The Service Under Test
 * ============================================================
 *  MyService depends on ExternalApi (injected via constructor).
 *
 *  Constructor Injection (Recommended):
 *   - Dependencies are explicit and visible
 *   - Easy to substitute with mocks in tests
 *   - No reflection or framework magic needed
 *
 *  Interview Tip: Prefer constructor injection over @Autowired
 *  field injection for better testability.
 * ============================================================
 */
public class MyService {

    private final ExternalApi externalApi;

    /**
     * Constructor Injection:
     *  - In tests      → pass Mockito.mock(ExternalApi.class)
     *  - In production → pass the real ExternalApi implementation
     */
    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    /** Fetches raw data from the external API. */
    public String fetchData() {
        return externalApi.getData();
    }

    /** Fetches data for a specific ID; returns "NOT_FOUND" if null. */
    public String fetchDataById(int id) {
        String result = externalApi.getDataById(id);
        return result != null ? result : "NOT_FOUND";
    }

    /** Sends payload to external API; returns friendly message. */
    public String publishData(String payload) {
        boolean success = externalApi.sendData(payload);
        return success ? "Published successfully" : "Publish failed";
    }

    /** Fetches data and converts to upper-case. */
    public String processData() {
        String raw = externalApi.getData();
        return raw != null ? raw.toUpperCase() : "EMPTY";
    }
}
