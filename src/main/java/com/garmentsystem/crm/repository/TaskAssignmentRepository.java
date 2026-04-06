package com.garmentsystem.crm.repository;

import com.garmentsystem.crm.model.Task;
import com.garmentsystem.crm.model.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {

    // Get all assignments for a task
    List<TaskAssignment> findByTask(Task task);

    // Get assignments by task id (VERY USEFUL)
    List<TaskAssignment> findByTask_TaskId(Long taskId);

    // Get assignments by group member
    List<TaskAssignment> findByGroupMemberId(Long groupMemberId);

    // Check duplicate assignment
    boolean existsByTaskAndGroupMemberId(Task task, Long groupMemberId);

    // Delete all assignments of a task
    void deleteByTask(Task task);
}