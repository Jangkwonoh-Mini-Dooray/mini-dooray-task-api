package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DefaultTaskServiceTest {
    @MockBean
    TaskRepository taskRepository;

    @Test
    void getAllByProjectId() {
        List<Task> allByProjectProjectId = new ArrayList<>();

        Task task = new Task();
        Task task2 = new Task();

        Project project = new Project();
        project.setProjectId(1L);

        task.setTaskId(1L);
        task.setProject(project);
        task2.setTaskId(2L);
        task2.setProject(project);

        allByProjectProjectId.add(task);
        allByProjectProjectId.add(task2);
        given(taskRepository.getAllByProject_ProjectId(anyLong()))
                .willReturn(allByProjectProjectId);

        List<Task> allTask = taskRepository.getAllByProject_ProjectId(1L);

        Assertions.assertThat(allTask).isNotEmpty();
    }
}