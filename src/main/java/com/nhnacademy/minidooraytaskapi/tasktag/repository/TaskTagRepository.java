package com.nhnacademy.minidooraytaskapi.tasktag.repository;

import com.nhnacademy.minidooraytaskapi.tasktag.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
}
