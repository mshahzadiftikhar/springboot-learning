package org.learning.spring.boot.learning.services;

import java.util.List;
import java.util.Optional;

import org.learning.spring.boot.learning.dto.CreateTaskDTO;
import org.learning.spring.boot.learning.dto.PatchTaskDTO;
import org.learning.spring.boot.learning.dto.PutTaskDTO;
import org.learning.spring.boot.learning.jpa.Task;
import org.learning.spring.boot.learning.jpa.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public String getHelloWorld() {
		return "Hello World";
	}
	
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	public Optional<Task> getTaskById(int id) {
		return taskRepository.findById(id);	
	}
	
	public Task create(CreateTaskDTO dto) {
	    Task task = new Task();
	    task.setName(dto.getName());
	    task.setDescription(dto.getDescription());
	    return taskRepository.save(task);
	}
	
	public Optional<Task> deleteTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
        	taskRepository.delete(task.get());
        }
        
        return task;
    }
	
	public Optional<Task> updateTask(int id, PutTaskDTO dto) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
        	task.get().setName(dto.getName());
            task.get().setDescription(dto.getDescription());
        }
    
        return task;
    }
	
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
}
