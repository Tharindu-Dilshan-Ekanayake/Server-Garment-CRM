package com.garmentsystem.crm.service;

import com.garmentsystem.crm.dto.TaskCreateWithMembers;
import com.garmentsystem.crm.model.Task;
import com.garmentsystem.crm.model.TaskAssignment;
import com.garmentsystem.crm.model.TaskStatus;
import com.garmentsystem.crm.repository.TaskAssignmentRepository;
import com.garmentsystem.crm.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskAssignmentRepository taskAssignmentRepository;

    public Task createTask(TaskCreateWithMembers dto) {

        // Create the task
        Task task = Task.builder()
                .item(dto.getItem())
                .itemQuantity(dto.getItemQuantity())
                .qmId(dto.getQmId())
                .groupLeaderId(dto.getGroupLeaderId())
                .status(TaskStatus.NOT_STARTED)
                .finishedQuantity(0)
                .build();

        Task savedTask = taskRepository.save(task);

        // combined group members + leader (avoid duplicates)
        Set<Long> allMembers = new HashSet<>(dto.getGroupMemberIds());
        allMembers.add(dto.getGroupLeaderId());

        //Insert into task_assignment
        for (Long memberId : allMembers) {

            TaskAssignment assignment = TaskAssignment.builder()
                    .task(savedTask)
                    .groupMemberId(memberId)
                    .completedQty(0)
                    .rejectedQty(0)
                    .approvedQty(0)
                    .build();

            taskAssignmentRepository.save(assignment);
        }


        return savedTask;
    }

    public List getTasks() {
        return taskRepository.findAll();
    }
}
