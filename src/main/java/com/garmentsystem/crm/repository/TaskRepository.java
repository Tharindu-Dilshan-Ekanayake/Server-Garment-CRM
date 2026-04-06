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

    // Get tasks by QM
    List<Task> findByQmId(Long qmId);

    // Optional: filter by status + leader
    List<Task> findByStatusAndGroupLeaderId(TaskStatus status, Long groupLeaderId);
}