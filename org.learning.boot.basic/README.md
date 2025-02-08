IDE: SpringToolsSuite4 by Eclipse (https://spring.io/tools)

Phase 1:
- Create a Spring Starter Project will Spring Web and Spring Dev tools dependencies.
- This will contains a main class Application.java with the @SpringBootApplication annotation
- Right click on the project and click Run As -> Spring Boot App, it will start spring boot application will contains an embedded tomcat server
- Default server address is localhost:8080

Phase 2:
- Add RestController for implementing REST API calls using @RestController 
- Add methods for GET method using @GetMapping("/hello") annontation. 
- Run application and verify by running following URLs: http://localhost:8080/bye   http://localhost:8080/hello
