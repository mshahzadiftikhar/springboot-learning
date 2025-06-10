package org.learning.spring.boot.learning.controllers;

import java.util.List;
import java.util.Optional;

import org.learning.spring.boot.learning.jpa.Task;
import org.learning.spring.boot.learning.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
} 
