package com.garmentsystem.crm.repository;

import com.garmentsystem.crm.model.Task;
import com.garmentsystem.crm.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Get all tasks by status
    List<Task> findByStatus(TaskStatus status);

    // Get tasks by group leader
    List<Task> findByGroupLeaderId(Long groupLeaderId);

    // Optional: filter by status + leader
    List<Task> findByStatusAndGroupLeaderId(TaskStatus status, Long groupLeaderId);
}