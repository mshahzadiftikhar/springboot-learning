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
- REST annotations:
  - @RestController: Tells spring framework that this class is a rest controller
  - @GetMapping("/users"): GET - endpoint for /users URL
  - @GetMapping("/users/{id}"): for acceesing this 'id', @PathVariable will be used
  - @PathVariable: Extract variable from URL: getUserByID(@PathVariable Long id)
  - @PostMapping("/users"): POST - adds stuff in datasource 
  - @RequestBody: This RequestBody will contains data (in JSON form)
  - @PutMapping("/users/{id}"): updateUser(@PathVariable Long id, @RequestBody Users userDetails)
  - @DeleteMapping("/{id}")
- Workflow 
  - Client Request → Controller → Service → Repository → Database
  - Response      ←          ←          ←           ←

Phase 3: 
- Add in-memory H2 database support using Java Persistance APIs (JPA).
- Hibernate is the default JPA provider. JPA is a specification that describes how to manage relational data in Java applications. It provides a way to map Java objects to database tables (Object-Relational Mapping or ORM
- Important annotations:
  - @Service: Acts as a leyers between database and controller 
  - @Entity: Means class name is a table of database
  - @Table(name = "users"): Used with @Entity, if table name is different than class name
  - @Data: from lombok project, this automatically generates getters/setters/constructors
  - @Id: Primary key
  - @GeneratedValue(strategy = GenerationType.IDENTITY): auto-increment ID
  - @Autowired: Tells Spring to inject the instance into this field

- For creating requests like POST, PUT, DELETE, you can use following:	
  - postman
  - REST Client: a vscode extension, save file with .http extension and then make calls. Refer to test.http file in the same folder

Phase 4:
- Add test cases for UserController and HelloController
- Once you create spring project, a class will be generated under src/test/java
- @SpringBootTest: Indicates that this class will be used to writing test cases
- @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT): Indicates that port can be random for running test cases
- @Test: Any method containing this annotation will be considered as test
- @LocalServerPort: random port on which http calls for testing will be made. Usage: private int port;
- private TestRestTemplate restTemplate; for making http API calls 