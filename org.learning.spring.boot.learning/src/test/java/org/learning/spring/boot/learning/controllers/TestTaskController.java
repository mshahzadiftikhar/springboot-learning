package org.learning.spring.boot.learning.controllers;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.learning.spring.boot.learning.jpa.Task;
import org.learning.spring.boot.learning.services.TaskService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable security filters for testing
public class TestTaskController{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void testGetTaskById_success() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setName("Test Task");
        task.setDescription("Test Desc");

        Mockito.when(taskService.getTaskById(1)).thenReturn(Optional.of(task));

        mockMvc.perform(get("/tasks/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Test Task"));
    }

}
