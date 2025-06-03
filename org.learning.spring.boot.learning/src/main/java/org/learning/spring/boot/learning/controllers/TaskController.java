package org.learning.spring.boot.learning.controllers;

import java.util.List;

import org.learning.spring.boot.learning.jpa.Task;
import org.learning.spring.boot.learning.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@GetMapping("/hello")
	public String getHelloWorld() {
		return taskService.getHelloWorld();
	}
	
	@GetMapping("tasks/allTasks")
	public List<Task> getAlltasks() {
		return taskService.getAllTasks();
	}
} 
