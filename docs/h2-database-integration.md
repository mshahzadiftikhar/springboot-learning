# Integrating H2 Database

### Add Dependency

We need to add dependency for H2 databse in [pom.xml](../org.learning.spring.boot.learning/pom.xml) file.
 - Add h2 database dependency in dependencies tag:
    ```
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```
 - Add JPA dependency in dependencies tag:
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```
You can get this dependency code from spring starter project. Open this [page](https://start.spring.io/), click on Add dependencies, search for H2 and then click on explore; from there you can see it under dependencies tag in pom.xml

### Add Database Configurations

We need to update [application.properties](../org.learning.spring.boot.learning/src/main/resources/application.properties) file for H2 database configutations

```
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.platform=h2

# Show SQL queries in console (optional)
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

# Enable H2 Console (web-based)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Create Table

We need to define a class which represents a database table: [Task.Java](../org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/jpa/Task.java)
```
@Entity
@Table(name = "Task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String name;
	public String description;
}
```
Annotations used above:

- `@Entity` – Declares this class as a JPA entity mapped to a database table.
- `@Table(name = "Task")` – Specifies the table name in the database as "Task". If this is not specified, class name is used as table name.
- `@Id` – Marks the field as the primary key of the entity.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` – Auto-generates the primary key using the database's identity column feature.

### Create JPA Repository

Create JPA repository. By extending JpaRepository, TaskRepository automatically inherits many useful CRUD (Create, Read, Update, Delete) operations. We can add methods for custom queries.
```
public interface TaskRepository extends JpaRepository<Task, Integer> {}
```

### Add Repositiry in Service
```
@Autowired
private TaskRepository taskRepository;

public List<Task> getAllTasks() {
    return taskRepository.findAll();
}
	
```

### Call Service function from Controller
```
@GetMapping("tasks/allTasks")
public List<Task> getAlltasks() {
    return taskService.getAllTasks();
}
```

