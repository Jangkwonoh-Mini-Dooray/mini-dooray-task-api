package com.nhnacademy.minidooraytaskapi.task.repository;

import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> getAllByProject_ProjectId(Long projectId);
}
