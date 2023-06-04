package com.nhnacademy.minidooraytaskapi.task.repository;

import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
