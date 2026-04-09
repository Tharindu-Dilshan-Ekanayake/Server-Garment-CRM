package com.garmentsystem.crm.controller;

import com.garmentsystem.crm.dto.TaskAssignmentUpdateDTO;
import com.garmentsystem.crm.dto.TaskCreateWithMembers;
import com.garmentsystem.crm.dto.TaskUpdateDTO;
import com.garmentsystem.crm.model.Task;
import com.garmentsystem.crm.model.TaskAssignment;
import com.garmentsystem.crm.config.JwtService;
import com.garmentsystem.crm.service.TaskAssignmentService;
import com.garmentsystem.crm.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor

public class TaskController {

    private final TaskService taskService;
    private final TaskAssignmentService service;
    private final JwtService jwtService;

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
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
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

    @GetMapping("/gettask/teamleader/{userid}")
    @SecurityRequirement(name = "bearerAuth")
    public List<Task> getTasksByTeamLeaderId(@PathVariable Long userid) {
        return taskService.getTasksByTeamLeaderId(userid);
    }

    // Leader tasks from token (no need to pass user id)
    @GetMapping("/leader/tasks")
    @SecurityRequirement(name = "bearerAuth")
    public List<Task> getMyLeaderTasks(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        Long leaderId = jwtService.extractUserId(token);
        if (leaderId == null) {
            throw new RuntimeException("Invalid token: missing userId");
        }

        return taskService.getTasksByTeamLeaderId(leaderId);
    }

    // Group member tasks from token (returns tasks assigned to this user as a member)
    @GetMapping("/member/tasks")
    @SecurityRequirement(name = "bearerAuth")
    public List<Task> getMyMemberTasks(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        Long memberId = jwtService.extractUserId(token);
        if (memberId == null) {
            throw new RuntimeException("Invalid token: missing userId");
        }

        return taskService.getTasksAssignedToMember(memberId);
    }
}
