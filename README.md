# Spring Boot Learning Journey

This repository documents my step-by-step learning process for **Spring Boot Backend Development**. Each phase represents a key learning milestone with relevant concepts, code implementation, and best practices.

---

## Setting Up a Spring Boot Project

You can use any of the folowing IDEs. I have used spring tools for eclipse to implement this project.  
 - [Spring Tools for Eclipse](https://spring.io/tools)
 - [IntelliJ](https://www.jetbrains.com/idea/download/?section=windows) 
 - [Spring Tools for Visual Studio Code](https://spring.io/tools)

Follow these steps for creating a new spring project: 

1. Create a [Spring Starter Project](https://start.spring.io/) with the following configurations:
   - Select Maven from Projects and Java from Languages (can be changed as per requirement)
   - Select JAR from Packaging and Java version of your choice (using 17)
   - Add dependencies: 
      - Spring Web (for REST APIs)
      - Spring Boot DevTools (for hot reload during development)
2. Import the starter project into IDE
3. A main class [Application.java](./org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/Application.java) is generated with the `@SpringBootApplication` annotation.
4. Run the application:
   ```
   Right-click project → Run As → Spring Boot App
   ```
5. By default, the Spring Boot application starts with an embedded Tomcat server on `localhost:8080`.

---
## Controller & Services

All http requests are handled by the controllers. Service acts as a layer between the Controller and the data source (e.g., database), encapsulating business logic and reusable operations.
 - All service classes should be annotated with `@Service`
   ```
   @Service
   public class TaskService {

      public String getHelloWorld() {
         return "Hello World";
      }
   }
   ```
   For now, we aren't fetching data from any database. Just returning the hardcoded string to see how overall things works. 
 - All Controller classes should be annotated with `@RestController`
   ```
   @RestController
   public class TaskController {
      @Autowired
      TaskService taskService;
      
      @GetMapping("/hello")
      public String getHelloWorld() {
         return taskService.getHelloWorld();
      }
   }
   ```
   `@Autowired`: It tells Spring to automatically inject a required bean (object) into a class.
 - Now  you should see _Hello World_ if you run following URL: localhost:8080/hello

 ## Integrating H2 Database

 H2 is in memory database that is useful for testing purpose. We will be using Java persistent APIs (JPA), where we can map java objects to database tables.   
 For details, see this page: [H2 Database Integration](./docs/h2-database-integration.md)

 ## HTTP Requests

 Lets looks at details of each HTTP Request: GET, POST, DELETE, PUT, PATCH: [HTTP Requests](./docs/http-requests.md)


## CICD  - GitHub Actions 
We can write a CICD file that automatically build, test and deploy our spring boot application using github actions feature.
  - We need to place all files for the github actions under .github/workflows folder
  - File extension should be yaml or yml. Name can be any and there can be multiple files, each independent of each other. We can write seperate files for build, test and deploy stages. 
  - For reference see: [ci-cd.yaml](.github/workflows/ci-cd.yml) 

## Testing
Spring Boot provides powerful support for unit testing and integration testing. It used Junit for assertions and Mockito for moking.  
For details see: [Testing in Spring Boot](docs/testing.md)

 ## Spring Boot Actuator

Spring Boot Actuator provides production-ready features to help you monitor and manage your application via REST endpoints.

 - Add the following dependency in your `pom.xml`:

   ```
   <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```
 - By default very limited number of endpoints like `/actuator/health` are available. You can enable all or specific in .properties file.
   ```
   # Expose specific endpoints
   management.endpoints.web.exposure.include=health,info,metrics

   # To expose all endpoints (for development only)
   management.endpoints.web.exposure.include=*

   # Show detailed health info
   management.endpoint.health.show-details=always
   ```
 - Some common endpoints  

   | Endpoint             | Description                                  |
   | -------------------- | -------------------------------------------- |
   | `/actuator/health`   | Application health status                    |
   | `/actuator/info`     | Custom info like app version or author       |
   | `/actuator/metrics`  | Performance metrics like memory, CPU, etc.   |
   | `/actuator/env`      | All environment properties and config values |
   | `/actuator/beans`    | List of Spring beans in the app              |
   | `/actuator/mappings` | All HTTP endpoint mappings                   |
   | `/actuator/loggers`  | View or modify logging levels at runtime     |

## Swagger Documentation  
Swagger helps you document, visualize, and test your REST APIs through an interactive UI.
- Add dependency in pom.xml file
   ```
   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
   </dependency>
   ```
   Swagger documentation will be available at: http://localhost:8080/swagger-ui/index.html
 - Annotations:
     - `@Tag`: Specified at the top of controller to define a section in html page. By default, controller name is used. Example: 
         ```
         @Tag(name = "Task Controller", description = "Controller for managing tasks")
         ```
     - `@Operation`: Explains each http request method with summary and description parameters
         ```
         @Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
         ```
 - For disabling swagger documentation from application.properties file, normally done in production env
   ```
   springdoc.api-docs.enabled=false
   ```

## Exception Handling

 - Local Exception Handling:
   Exception handling can be performed inside controller
      - Create [TaskNotFoundException](org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/exceptions/TaskNotFoundException.java) class, which extends from RuntimeException and simply prints the message being passed. 
      - Add Method in controller which accepts exception handling and returen response as ResponseEntity
         ```
         @ExceptionHandler(TaskNotFoundException.class)
         public ResponseEntity<String> handleTaskNotFound(TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
         }
         ``` 
      - Throw exception from http method if id is not found
         ```
         public Task getTaskById(@PathVariable int id) {
            return taskService.getTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
         }
         ```
- Global Exception Handling  
   - Create a class for global exception handling: [GlobalExceptionHandler](org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/exceptions/GlobalExceptionHandler.java)
    - `@ControllerAdvice` annotation defines that this class is global exception
    - Its a more cleaner way to group all exception handling at one place

## Profiles

Spring Profiles provide a way to segregate parts of your application configuration and make it only available in certain environments (like `dev`, `test`, `prod`).

---

### Why Use Profiles?

- Separate configurations for different environments  
- Avoid hardcoding environment-specific values  
- Conditionally load beans  
- Simplify deployment and testing  

---

### Profile-Specific Property Files

Spring Boot automatically loads `application-<profile>.properties` based on the active profile.

Example files:
- `application.properties` — base config
- `application-dev.properties` — for development
- `application-test.properties` — for testing
- `application-prod.properties` — for production

---

### Activating a Profile

 - Via Environment Variable (Recommended)
   ```bash
   export SPRING_PROFILES_ACTIVE=dev     # Linux/macOS
   set SPRING_PROFILES_ACTIVE=dev        # Windows CMD
   ```
 - Via CLI
   ```
   java -jar myapp.jar --spring.profiles.active=dev
   ```
 - You can env variables in your preferred IDE as well if you are in development phase. 
