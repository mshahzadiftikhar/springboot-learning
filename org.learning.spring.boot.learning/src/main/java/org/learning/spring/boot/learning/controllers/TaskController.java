package org.learning.spring.boot.learning.controllers;

import java.util.List;
import java.util.Optional;

import org.learning.spring.boot.learning.dto.CreateTaskDTO;
import org.learning.spring.boot.learning.dto.PatchTaskDTO;
import org.learning.spring.boot.learning.dto.PutTaskDTO;
import org.learning.spring.boot.learning.dto.ResponseTaskDTO;
import org.learning.spring.boot.learning.exceptions.ResourceNotFoundException;
import org.learning.spring.boot.learning.exceptions.TaskNotFoundException;
import org.learning.spring.boot.learning.jpa.Task;
import org.learning.spring.boot.learning.services.TaskService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("tasks")
@Tag(name = "Task Controller", description = "Controller for managing tasks")
public class TaskController {
	@Autowired
	TaskService taskService;

	Logger logger = org.slf4j.LoggerFactory.getLogger(TaskController.class);

	@GetMapping("/hello")
	public String getHelloWorld() {
		return taskService.getHelloWorld();
	}

	@GetMapping("allTasks")
	@Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
	public List<Task> getAlltasks() {
		logger.info("Fetching all tasks");
		return taskService.getAllTasks();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get task by ID", description = "Returns a task by its ID")
	public Task getTaskById(@PathVariable int id) {
		return taskService.getTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
	}

	@PostMapping("create")
	public ResponseTaskDTO createTask(@RequestBody CreateTaskDTO dto) {
		Task task = taskService.create(dto);
		ResponseTaskDTO response = new ResponseTaskDTO(task.getId(), task.getName());
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseTaskDTO> deleteById(@PathVariable int id) {
	    Optional<Task> task = taskService.deleteTaskById(id);
	    if (task.isPresent()) {
	        Task deleted = task.get();
	        ResponseTaskDTO dto = new ResponseTaskDTO(deleted.getId(), deleted.getName());
	        return ResponseEntity.ok(dto);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@PutMapping("/{id}")
	public Optional<Task> updateTask(@PathVariable int id, @RequestBody @Valid PutTaskDTO dto) {
		return taskService.updateTask(id, dto);
	}
	
	@PatchMapping("/{id}")
	public Task patchTask(@PathVariable int id, @RequestBody @Valid PatchTaskDTO dto) {
		return taskService.patchTask(id, dto).orElseThrow(() -> new RuntimeException("Task with ID " + id + " not found"));
	}

	@ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFound(TaskNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
