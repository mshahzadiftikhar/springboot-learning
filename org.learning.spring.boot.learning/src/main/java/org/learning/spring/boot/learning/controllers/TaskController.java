package org.learning.spring.boot.learning.controllers;

import java.util.List;
import java.util.Optional;

import org.learning.spring.boot.learning.dto.CreateTaskDTO;
import org.learning.spring.boot.learning.dto.PatchTaskDTO;
import org.learning.spring.boot.learning.dto.PutTaskDTO;
import org.learning.spring.boot.learning.dto.ResponseTaskDTO;
import org.learning.spring.boot.learning.jpa.Task;
import org.learning.spring.boot.learning.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tasks")
public class TaskController {
	@Autowired
	TaskService taskService;

	@GetMapping("/hello")
	public String getHelloWorld() {
		return taskService.getHelloWorld();
	}

	@GetMapping("allTasks")
	public List<Task> getAlltasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/{id}")
	public Optional<Task> getTaskById(@PathVariable int id) {
		return taskService.getTaskById(id);
	}

	@PostMapping("create")
	public ResponseTaskDTO createTask(@RequestBody CreateTaskDTO dto) {
		Task task = taskService.create(dto);
		ResponseTaskDTO response = new ResponseTaskDTO(task.getId(), task.getName());
		return response;
	}
	
	@DeleteMapping("/{id}")
	public Optional<Task> deleteById(@PathVariable int id) {
		return taskService.deleteTaskById(id);
	}
	
	@PutMapping("/{id}")
	public Optional<Task> updateTask(@PathVariable int id, @RequestBody @Valid PutTaskDTO dto) {
		return taskService.updateTask(id, dto);
	}
	
	@PatchMapping("/{id}")
	public Optional<Task> patchTask(@PathVariable int id, @RequestBody @Valid PatchTaskDTO dto) {
		return taskService.patchTask(id, dto);
	}
}
