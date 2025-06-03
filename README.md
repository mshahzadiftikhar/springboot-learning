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
