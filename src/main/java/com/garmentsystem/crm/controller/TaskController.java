package com.garmentsystem.crm.controller;

import com.garmentsystem.crm.dto.TaskCreateWithMembers;
import com.garmentsystem.crm.model.Task;
import com.garmentsystem.crm.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor

public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create-task")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateWithMembers dto) {

        Task task = taskService.createTask(dto);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/get-task")
    @SecurityRequirement(name = "bearerAuth")
    public List<Task> getAllTasks() {
        return taskService.getTasks();
    }
}
