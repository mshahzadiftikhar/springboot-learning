# Testing in Spring Boot

| Type             | Purpose                                | Tools                   |
| ---------------- | -------------------------------------- | ----------------------- |
| Unit Test        | Test individual methods in isolation   | JUnit + Mockito         |
| Integration Test | Test how components work together      | `@SpringBootTest`       |
| Web Layer Test   | Test controller layer only             | `@WebMvcTest` + MockMvc |
| Data Layer Test  | Test repositories with DB (real or H2) | `@DataJpaTest`          |


## Unit Testing

### Testing Service Layer
 - Test business logic in isolation (usually the service layer).
 - Mock dependencies (e.g., repository, external APIs).
 - Focus on inputs and outputs, not the wiring or environment.
 - Fast and deterministic tests.
 - Annotation used:
    | Annotation                            | Purpose                                   |
    | ------------------------------------- | ----------------------------------------- |
    | `@ExtendWith(MockitoExtension.class)` | Enables Mockito in JUnit 5                |
    | `@Mock`                               | Creates a mock object                     |
    | `@InjectMocks`                        | Injects mocks into the class being tested |
    | `@BeforeEach`                         | Method to set up test data                |
    | `@Test`                               | Denotes a test method                     |
  - See this for examples: [TestTaskService](../org.learning.spring.boot.learning/src/test/java/org/learning/spring/boot/learning/services/TestTaskService.java)

  ### Testing Controller Layer
   - Refrence: [TestTaskController](../org.learning.spring.boot.learning/src/test/java/org/learning/spring/boot/learning/controllers/TestTaskController.java)
    - Annotations:  
        | Annotation                            | Purpose                                   |
        | ------------------------------------- | ----------------------------------------- |
        | `@WebMvcTest` | Loads only MVC components (controller layer)                |
        | `@MockitoBean`                               | Replaces a Spring bean with a Mockito mock                     |
