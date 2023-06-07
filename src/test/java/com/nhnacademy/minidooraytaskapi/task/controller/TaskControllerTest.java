package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.task.dto.PostTaskDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TaskController.class)
@DisplayName("Task : Controller 테스트")
class TaskControllerTest {
    @MockBean
    TaskService taskService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
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
                .thenReturn(List.of(new TaskDto(task.getTaskId(), task.getTaskWriterMemberId(), task.getMilestone(), task.getTitle()),
                        new TaskDto(task2.getTaskId(), task2.getTaskWriterMemberId(), task2.getMilestone(), task.getTitle())));


        mockMvc.perform(get("/projects/{project-id}/posts", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].taskId", equalTo(1)));
    }

    @Test
    @DisplayName("프로젝트에 존재하는 특정 업무 찾아오기")
    void getTask() throws Exception {
        Task task = new Task();

        Project project = new Project();
        project.setProjectId(1L);
        project.setName("ggg");
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("활성");
        project.setProjectStatus(projectStatus);

        task.setTaskId(1L);
        task.setProject(project);

        when(taskService.getTaskByTaskIdAndProjectId(anyLong(), anyLong()))
                .thenReturn(new TaskDto(task.getTaskId(), task.getTaskWriterMemberId(), task.getMilestone(), task.getTitle()));


        mockMvc.perform(get("/projects/{project-id}/posts/{task-id}", project.getProjectId(), task.getTaskId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.taskId", equalTo(1)));
    }

    @Test
    @DisplayName("프로젝트에 업무 생성 #실패 RequestBody 가 오지 않음")
    void createTask() throws Exception {
        Project project = new Project();
        project.setProjectId(1L);

        mockMvc.perform(post("/projects/{project-id}/posts", project.getProjectId()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("프로젝트에 업무 생성 #성공")
    void createTask2() throws Exception {

        PostTaskDto task = new PostTaskDto();

        Project project = new Project();
        project.setProjectId(1L);
        project.setName("ggg");
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("활성");
        project.setProjectStatus(projectStatus);


        task.setTaskWriterMemberId("naht94");
        task.setTitle("세번째 업무");
        task.setContent("테스트 업무");

        when(taskService.postTask(any(), anyLong()))
                .thenReturn(1L);

        mockMvc.perform(post("/projects/{project-id}/posts", project.getProjectId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"taskId\":1}"));
    }
    @Test
    @DisplayName("프로젝트에 업무 수정 #실패 RequestBody 가 오지 않음")
    void modifyTask() throws Exception {
        Project project = new Project();
        project.setProjectId(1L);

        Task task = new Task();
        task.setTaskId(1L);

        mockMvc.perform(put("/projects/{project-id}/posts/{task-id}", project.getProjectId(), task.getTaskId()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("프로젝트에 업무 수정 #성공")
    void modifyTask2() throws Exception {
        Task task = new Task();
        PostTaskDto taskDto = new PostTaskDto();

        Project project = new Project();
        project.setProjectId(1L);
        project.setName("ggg");
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("활성");
        project.setProjectStatus(projectStatus);

        task.setTaskId(1L);

        taskDto.setTaskWriterMemberId("naht94");
        taskDto.setTitle("세번째 업무");
        taskDto.setContent("테스트 업무");

        when(taskService.putTask(any(), anyLong(), anyLong()))
                .thenReturn(1L);

        mockMvc.perform(put("/projects/{project-id}/posts/{task-id}", project.getProjectId(), task.getTaskId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"taskId\":1}"));
    }
    @Test
    @DisplayName("업무 삭제")
    void deleteTask() throws Exception {
        Task task = new Task();
        task.setTaskId(1L);

        doNothing().when(taskService).deleteTask(anyLong());

        mockMvc.perform(delete("/projects/posts/{task-id}", task.getTaskId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("OK")));

        verify(taskService, times(1)).deleteTask(task.getTaskId());
    }

}