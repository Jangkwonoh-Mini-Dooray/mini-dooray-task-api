package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
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
        given(taskRepository.getAllByProjectProjectId(anyLong()))
                .willReturn(List.of(new TaskDto() {
                    @Override
                    public Long getTaskId() {
                        return task.getTaskId();
                    }

                    @Override
                    public String getTaskWriterMemberId() {
                        return task.getTaskWriterMemberId();
                    }

                    @Override
                    public Milestone getMilestone() {
                        return task.getMilestone();
                    }

                    @Override
                    public String getTitle() {
                        return task.getTitle();
                    }
                }, new TaskDto() {
                    @Override
                    public Long getTaskId() {
                        return task2.getTaskId();
                    }

                    @Override
                    public String getTaskWriterMemberId() {
                        return task2.getTaskWriterMemberId();
                    }

                    @Override
                    public Milestone getMilestone() {
                        return task2.getMilestone();
                    }

                    @Override
                    public String getTitle() {
                        return task2.getTitle();
                    }
                }));

        List<TaskDto> allTask = taskService.getAllByProjectId(1L);

        Assertions.assertThat(allTask).isNotEmpty().hasSize(2);
        Assertions.assertThat(allTask.get(0).getTaskId()).isEqualTo(1L);
    }
}