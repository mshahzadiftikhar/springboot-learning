# HTTP Requests
### GET

 - Add method in service class
    ```
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);	
    }
    ```
  - Add method in controller class
    ```
    @GetMapping("allTasks")
	public List<Task> getAlltasks() {
		return taskService.getAllTasks();
	}
	
	@GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
	}
    ```
  - Annotations  
    - `@GetMapping`	Maps HTTP GET requests to this method  
    - `@PathVariable`	Extracts values from URL path (e.g., /tasks/5)
    - `RequestMapping` If specified at controller level, all incoming requests are mapped to that path. Lets say we are using @RequestMapping("tasks"), then all mapping in the controller will be prepended with tasks. Like  @GetMapping("allTasks") is actually  @GetMapping("tasks/allTasks"). RequestMapping annotation can be method specific as well. See details [here](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html).

### POST
 - Create DTOs:  
 [CreateTaskDTO](../org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/dto/CreateTaskDTO.java)  
 [ResponseTaskDTO](../org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/dto/ResponseTaskDTO.java)
  - Create method in service
    ```
        public Task create(CreateTaskDTO dto) {
            Task task = new Task();
            task.setName(dto.getName());
            task.setDescription(dto.getDescription());
            return taskRepository.save(task);   
        }
    ```
  - Create method in controller
    ```
        @PostMapping("create")
        public ResponseTaskDTO createTask(@RequestBody CreateTaskDTO dto) {
            Task task = taskService.create(dto);
            ResponseTaskDTO response = new ResponseTaskDTO(task.getId(), task.getName());
            return response;
        }
    ```
- Annotations:
     - `@PostMapping`: Maps HTTP POST requests to this method. Commonly used for creating new resources
     - `@RequestBody`: Binds the incoming JSON request body to a Java object (e.g., `TaskCreateDTO`)

### DELETE
- Create method in service
    ```
    public Optional<Task> deleteTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
        	taskRepository.delete(task.get());
        }
        
        return task;
    }
    ```
- Create method in controller
    ```
    @DeleteMapping("/{id}")
	public Optional<Task> deleteById(@PathVariable int id) {
		return taskService.deleteTaskById(id);
	}
    ```
- Annotations:
     - `@DeleteMapping`: Maps HTTP DELET requests to this method.

### PUT 
 - Add dependency for validation
    ```
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    ```
 - Add DTO containg validatios: [PutTaskDTO](../org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/dto/PutTaskDTO.java)
 - Add method in service
    ```
    public Optional<Task> updateTask(int id, PutTaskDTO dto) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
        	task.get().setName(dto.getName());
            task.get().setDescription(dto.getDescription());
        }
    
        return task;
    }
    ```
 - Add method in controller
    ```
    @PutMapping("/{id}")
	public Optional<Task> updateTask(@PathVariable int id, @RequestBody @Valid PutTaskDTO dto) {
		return taskService.updateTask(id, dto);
	}
    ```
 - Annotations:
   - `@PutMapping`  
      Maps HTTP PUT requests. Used to **replace** an existing resource completely.

    - `@Valid`  
      Triggers validation on the DTO fields using annotations like `@NotEmpty`.
      
### PATCH

Patch is very similar to PUT, for patch we need to use `@PatchMapping` annottaion and use a DTO that don't put constraints on name and desciption of the task.
 - Create DTO: [PatchTaskDTO](../org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/dto/PatchTaskDTO.java)
 - Add method in service
    ```
    public Optional<Task> patchTask(int id, PatchTaskDTO dto) {
		Optional<Task> task = taskRepository.findById(id);

		if (task.isPresent()) {
			if (dto.getName() != null) {
				task.get().setName(dto.getName());
			}
			if (dto.getDescription() != null) {
				task.get().setDescription(dto.getDescription());
			}
			
			taskRepository.save(task.get());
		}

		return task;
	}
    ```
 - Add methiod in Controller
    ```
    @PatchMapping("/{id}")
	public Optional<Task> patchTask(@PathVariable int id, @RequestBody @Valid PatchTaskDTO dto) {
		return taskService.patchTask(id, dto);
	}
    ```
 - Annotations:
    - `@PatchMapping`: Maps HTTP PATCH requests. Used for partial updates to a resource.