# Mockito Hands-On Exercises

Interview preparation exercises for Mockito with JUnit 5.

## Project Structure
```
02-Mockito-Exercises/
├── pom.xml
├── src/main/java/com/interview/mockito/
│   ├── ExternalApi.java                         ← Interface (dependency to mock)
│   └── MyService.java                           ← Service Under Test
└── src/test/java/com/interview/mockito/
    ├── Exercise1_MockingAndStubbingTest.java    ← mock(), when().thenReturn()
    └── Exercise2_VerifyingInteractionsTest.java ← verify(), @Mock, @InjectMocks
```

## How to Run
```bash
mvn test
```

## Exercises Covered

| Exercise | Topic | Key Concepts |
|---|---|---|
| Exercise 1 | Mocking & Stubbing | `mock()`, `when().thenReturn()`, `thenThrow()` |
| Exercise 2 | Verifying Interactions | `verify()`, `times()`, `never()`, `@Mock`, `@InjectMocks` |

## Key Concepts

### Mock vs Stub vs Spy
| Term | Description |
|---|---|
| **Mock** | Fake object; all methods return default (0/null/false) |
| **Stub** | Configuring mock to return a specific value |
| **Spy** | Real object that also records method calls |

### Stubbing
```java
when(mock.getData()).thenReturn("value");
when(mock.getData()).thenThrow(new RuntimeException("down"));
when(mock.getDataById(anyInt())).thenReturn("result");
```

### Verifying
```java
verify(mock).getData();                  // called once
verify(mock, times(3)).getData();        // called 3 times
verify(mock, never()).getData();         // never called
verify(mock, atLeast(2)).getData();      // called >= 2 times
verifyNoMoreInteractions(mock);          // no unexpected calls
```

### Annotations
```java
@ExtendWith(MockitoExtension.class)      // enable Mockito
@Mock ExternalApi mockApi;               // create mock
@InjectMocks MyService service;          // inject mocks into service
```
