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