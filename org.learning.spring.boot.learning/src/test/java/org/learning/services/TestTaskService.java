package org.learning.services;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.learning.spring.boot.learning.jpa.Task;
import org.learning.spring.boot.learning.jpa.TaskRepository;
import org.learning.spring.boot.learning.services.TaskService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // Enables Mockito support in JUnit 5
public class TestTaskService {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task();
        task.setId(1);
        task.setName("Test Task");
        task.setDescription("This is a test task.");
    }

    /**
     * Tests the {@code getTaskById} method of {@link TaskService}.
     * <p>
     * This test verifies that when the {@code taskRepository} returns a {@link Task}
     * wrapped in an {@link Optional} for a given ID, the {@code taskService} correctly
     * retrieves and returns the same {@link Task} instance.
     * </p>
     * <ul>
     *   <li>Mocks the repository to return a predefined {@link Task} for ID {@code 1}.</li>
     *   <li>Invokes the service method with the same ID.</li>
     *   <li>Asserts that the returned {@link Optional} contains the expected {@link Task}.</li>
     * </ul>
     */
    @Test
    public void testGetTaskById() {
        // Mock the repository to return the task when findById is called with ID 1
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.getTaskById(1);
        assertEquals(task, foundTask.get());
    }

    @Test
    public void testGetTaskByIdNotFound() {
        when(taskRepository.findById(2)).thenReturn(Optional.empty());
        Optional<Task> foundTask = taskService.getTaskById(2);
        assertEquals(Optional.empty(), foundTask);
    }
    
}
