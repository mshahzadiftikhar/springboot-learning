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