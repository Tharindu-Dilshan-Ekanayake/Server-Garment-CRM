package com.garmentsystem.crm.controller;

import com.garmentsystem.crm.dto.TaskAssignmentUpdateDTO;
import com.garmentsystem.crm.dto.TaskCreateWithMembers;
import com.garmentsystem.crm.dto.TaskUpdateDTO;
import com.garmentsystem.crm.model.Task;
import com.garmentsystem.crm.model.TaskAssignment;
import com.garmentsystem.crm.service.TaskAssignmentService;
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
    private final TaskAssignmentService service;

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

    //get by id
    @GetMapping("/get-task/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    //delete by id
    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public String deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "Task deleted successfully";
    }

    //patch
    @PatchMapping("/update/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody TaskUpdateDTO dto
    ) {
        return taskService.updateTask(id, dto);
    }

    @PatchMapping("/tasks-assignment/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public TaskAssignment updateAssignment(
            @PathVariable Long id,
            @RequestBody TaskAssignmentUpdateDTO dto
    ) {
        return service.updateAssignment(id, dto);
    }
}
