# Spring Boot Learning Journey

This repository documents my step-by-step learning process for **Spring Boot Backend Development**. Each phase represents a key learning milestone with relevant concepts, code implementation, and best practices.

---

## **Phase 1: Setting Up a Spring Boot Project**

### **Steps:**

1. Create a Spring Starter Project with the following dependencies:
   - **Spring Web** (for REST APIs)
   - **Spring DevTools** (for hot reload during development)
2. A main class `Application.java` is generated with the `@SpringBootApplication` annotation.
3. Run the application:
   ```
   Right-click project → Run As → Spring Boot App
   ```
4. By default, the Spring Boot application starts with an embedded Tomcat server on `localhost:8080`.

---

## **Phase 2: Building REST APIs**

### **Key Concepts & Annotations:**

- `@RestController` - Defines a class as a REST API controller.
- `@GetMapping("/hello")` - Maps a GET request to an endpoint.
- `@PathVariable` - Extracts values from the URL.
- `@PostMapping("/users")` - Handles HTTP POST requests with `@RequestBody`.
- `@PutMapping("/users/{id}")` - Updates a resource.
- `@DeleteMapping("/users/{id}")` - Deletes a resource.

### **Example Endpoints:**

- `GET http://localhost:8080/hello`
- `GET http://localhost:8080/users/{id}`
- `POST http://localhost:8080/users` (with JSON body)

### **Request Flow:**

```
Client Request → Controller → Service → Repository → Database
Response ← ← ← ←
```

---

## **Phase 3: Integrating H2 Database with JPA**

### **Key Concepts:**

- **JPA (Java Persistence API)** - Maps Java objects to database tables.
- **Hibernate** - Default JPA provider in Spring Boot.
- **H2 Database** - An in-memory database useful for testing.

### **Important Annotations:**

- `@Service` - Business logic layer.
- `@Entity` - Marks a class as a database entity.
- `@Table(name = "users")` - Defines the table name.
- `@Id` - Primary key.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` - Auto-increment ID.
- `@Autowired` - Injects dependencies.

### **Tools for Testing API Requests:**

- **Postman**
- **VS Code REST Client Extension** (`.http` files for API requests)

---

## **Phase 4: Writing Unit & Integration Tests**

### **Key Testing Annotations:**

- `@SpringBootTest` - Loads the application context for testing.
- `@Test` - Marks a method as a test case.
- `@LocalServerPort` - Assigns a random port for testing.
- `TestRestTemplate` - Makes API calls in tests.

---

## **Phase 5: API Documentation with Swagger**

### **Steps to Integrate Swagger:**

1. Add `springdoc-openapi-starter-webmvc-ui` dependency in `pom.xml`.
2. Start the application and visit:\
   [**Swagger UI**](http://localhost:8080/swagger-ui.html)

### **Swagger Annotations:**

- `@Tag(name = "User Management", description = "APIs for managing users")`
- `@Operation(summary = "Get User by ID", description = "Fetch a user using their unique ID")`

To **disable Swagger in production**, set:

```properties
springdoc.api-docs.enabled=false
```

---

## **Upcoming Phases:**

### ✅ **Phase 6: Exception Handling & Validation**
