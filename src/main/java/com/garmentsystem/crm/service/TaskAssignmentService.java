package com.garmentsystem.crm.service;

import com.garmentsystem.crm.dto.TaskAssignmentUpdateDTO;
import com.garmentsystem.crm.model.Task;
import com.garmentsystem.crm.model.TaskAssignment;
import com.garmentsystem.crm.model.TaskStatus;
import com.garmentsystem.crm.repository.TaskAssignmentRepository;
import com.garmentsystem.crm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskAssignmentService {

    private final TaskAssignmentRepository assignmentRepository;
    private final TaskRepository taskRepository;

    public TaskAssignment updateAssignment(Long id, TaskAssignmentUpdateDTO dto) {

        TaskAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        // update values
        if (dto.getCompletedQty() != null) {
            assignment.setCompletedQty(dto.getCompletedQty());
        }

        if (dto.getRejectedQty() != null) {
            assignment.setRejectedQty(dto.getRejectedQty());
        }

        //  AUTO CALCULATION
        int completed = assignment.getCompletedQty() != null ? assignment.getCompletedQty() : 0;
        int rejected = assignment.getRejectedQty() != null ? assignment.getRejectedQty() : 0;

        int approved = completed - rejected;
        if (approved < 0) approved = 0;

        assignment.setApprovedQty(approved);

        TaskAssignment saved = assignmentRepository.save(assignment);

        //  UPDATE TASK TOTAL
        Task task = assignment.getTask();

        List<TaskAssignment> assignments = assignmentRepository.findByTask(task);

        int totalApproved = assignments.stream()
                .mapToInt(a -> a.getApprovedQty() != null ? a.getApprovedQty() : 0)
                .sum();

        task.setFinishedQuantity(totalApproved);

        //  AUTO COMPLETE TASK
        if (totalApproved >= task.getItemQuantity()) {
            task.setStatus(TaskStatus.COMPLETED);
        } else {
            task.setStatus(TaskStatus.IN_PROGRESS);
        }

        taskRepository.save(task);

        return saved;
    }
}