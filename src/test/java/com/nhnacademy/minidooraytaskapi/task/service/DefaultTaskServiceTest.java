package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.List;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class DefaultTaskServiceTest {
    @Autowired
    TaskService taskService;
    @MockBean
    TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllByProjectId() {
        Task task = new Task();
        Task task2 = new Task();

        Project project = new Project();
        project.setProjectId(1L);

        task.setTaskId(1L);
        task.setProject(project);
        task2.setTaskId(2L);
        task2.setProject(project);


        given(taskRepository.getTasks(anyLong()))
                .willReturn(List.of(new TaskDto(task.getTaskId(), task.getTaskWriterMemberId(), task.getMilestone(), task.getTitle()),
                        new TaskDto(task2.getTaskId(), task2.getTaskWriterMemberId(), task2.getMilestone(), task.getTitle())));

        List<TaskDto> allTask = taskService.getAllByProjectId(project.getProjectId());

        Assertions.assertThat(allTask).isNotEmpty().hasSize(2);
        Assertions.assertThat(allTask.get(0).getTaskId()).isEqualTo(task.getTaskId());
    }

    @Test
    void testGetTaskByTaskIdAndProjectId() {
        Task task = new Task();

        Project project = new Project();
        project.setProjectId(1L);

        task.setTaskId(1L);
        task.setProject(project);

        given(taskRepository.getTask(task.getTaskId(), project.getProjectId()))
                .willReturn(new TaskDto(task.getTaskId(), task.getTaskWriterMemberId(), task.getMilestone(), task.getTitle()));
        assertThat(taskService.getTaskByTaskIdAndProjectId(task.getTaskId(), project.getProjectId()));
    }
}