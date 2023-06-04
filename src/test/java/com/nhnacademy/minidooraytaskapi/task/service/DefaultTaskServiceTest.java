package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DefaultTaskServiceTest {
    @Autowired
    TaskRepository taskRepository;

    @Test
    void getAllByProjectId() {
        List<Task> allByProjectProjectId = taskRepository.getAllByProject_ProjectId(1L);

        Assertions.assertThat(allByProjectProjectId).isNotEmpty();
    }
}