package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import com.nhnacademy.minidooraytaskapi.task.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
class TaskControllerTest {
    @MockBean
    TaskService taskService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("프로젝트에 존재하는 모든 업무 찾아오기")
    void getTasks() throws Exception {
        Task task = new Task();
        Task task2 = new Task();

        Project project = new Project();
        project.setName("ggg");
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("활성");
        project.setProjectStatus(projectStatus);

        task.setTaskId(1L);
        task.setProject(project);
        task2.setTaskId(2L);
        task2.setProject(project);

        when(taskService.getAllByProjectId(anyLong()))
                .thenReturn(List.of(new TaskDto() {
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


        mockMvc.perform(get("/projections/{project-id}/posts", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].taskId", equalTo(1)));
    }
}