# Testing in Spring Boot

| Type             | Purpose                                | Tools                   |
| ---------------- | -------------------------------------- | ----------------------- |
| Unit Test        | Test individual methods in isolation   | JUnit + Mockito         |
| Integration Test | Test how components work together      | `@SpringBootTest`       |
| Web Layer Test   | Test controller layer only             | `@WebMvcTest` + MockMvc |
| Data Layer Test  | Test repositories with DB (real or H2) | `@DataJpaTest`          |


## Unit Testing
 - Test business logic in isolation (usually the service layer).
 - Mock dependencies (e.g., repository, external APIs).
 - Focus on inputs and outputs, not the wiring or environment.
 - Fast and deterministic tests.