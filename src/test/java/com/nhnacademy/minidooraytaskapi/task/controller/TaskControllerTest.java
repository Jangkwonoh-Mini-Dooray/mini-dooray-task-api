package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskRequestDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
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
    ObjectMapper objectMapper;

    @Test
    @DisplayName("프로젝트에 존재하는 모든 업무 찾아오기")
    void getTasks() throws Exception {
        Task task = new Task();
        Task task2 = new Task();

        Project project = new Project();
        ReflectionTestUtils.setField(project, "projectId", 1L);
        ReflectionTestUtils.setField(project, "name", "ggg");
        ReflectionTestUtils.setField(project, "projectId", 1L);

        ProjectStatus projectStatus = new ProjectStatus("활성");

        ReflectionTestUtils.setField(project, "projectStatus", projectStatus);

        ReflectionTestUtils.setField(task, "taskId", 1L);
        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L, "title", "content");
        task.save(taskRequestDto);
        task.setProject(project);
        ReflectionTestUtils.setField(task2, "taskId", 2L);
        task2.save(taskRequestDto);
        task2.setProject(project);

        when(taskService.getTasks(anyLong()))
                .thenReturn(List.of(new TaskDto(task.getTaskId(), task.getTaskWriterMemberId(), task.getMilestone(), task.getTitle(), task.getContent()),
                        new TaskDto(task2.getTaskId(), task2.getTaskWriterMemberId(), task2.getMilestone(), task2.getTitle(), task2.getContent())));


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
        ReflectionTestUtils.setField(project, "projectId", 1L);
        ReflectionTestUtils.setField(project, "name", "ggg");
        ReflectionTestUtils.setField(project, "projectId", 1L);
        ProjectStatus projectStatus = new ProjectStatus("활성");
        ReflectionTestUtils.setField(project, "projectStatus", projectStatus);
        ReflectionTestUtils.setField(task, "taskId", 1L);

        ReflectionTestUtils.setField(task, "taskId", 1L);
        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L, "title", "content");
        task.save(taskRequestDto);
        task.setProject(project);

        when(taskService.getTask(anyLong(), anyLong()))
                .thenReturn(new TaskDto(task.getTaskId(), task.getTaskWriterMemberId(), task.getMilestone(), task.getTitle(), task.getContent()));


        mockMvc.perform(get("/projects/{project-id}/posts/{task-id}", project.getProjectId(), task.getTaskId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.taskId", equalTo(1)));
    }

    @Test
    @DisplayName("프로젝트에 업무 생성 #실패 RequestBody 가 오지 않음")
    void createTask() throws Exception {
        Project project = new Project();
        ReflectionTestUtils.setField(project, "projectId", 1L);

        mockMvc.perform(post("/projects/{project-id}/posts", project.getProjectId()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("프로젝트에 업무 생성 #성공")
    void createTask2() throws Exception {

        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L, "세번째 업무", "테스트 업무");
        Project project = new Project();
        ReflectionTestUtils.setField(project, "projectId", 1L);
        ReflectionTestUtils.setField(project, "name", "ggg");
        ReflectionTestUtils.setField(project, "projectId", 1L);
        ProjectStatus projectStatus = new ProjectStatus("활성");
        ReflectionTestUtils.setField(project, "projectStatus", projectStatus);

        when(taskService.postTask(any(), anyLong()))
                .thenReturn(1L);

        mockMvc.perform(post("/projects/{project-id}/posts", project.getProjectId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"taskId\":1}"));
    }
    @Test
    @DisplayName("프로젝트에 업무 수정 #실패 RequestBody 가 오지 않음")
    void modifyTask() throws Exception {
        Project project = new Project();
        ReflectionTestUtils.setField(project, "projectId", 1L);
        Task task = new Task();
        ReflectionTestUtils.setField(task, "taskId", 1L);
        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L, "세번째 업무", "테스트 업무");
        task.save(taskRequestDto);

        mockMvc.perform(put("/projects/{project-id}/posts/{task-id}", project.getProjectId(), task.getTaskId()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("프로젝트에 업무 수정 #성공")
    void modifyTask2() throws Exception {
        Task task = new Task();

        Project project = new Project();
        ReflectionTestUtils.setField(project, "projectId", 1L);
        ReflectionTestUtils.setField(project, "name", "ggg");
        ReflectionTestUtils.setField(project, "projectId", 1L);
        ProjectStatus projectStatus = new ProjectStatus("활성");
        ReflectionTestUtils.setField(project, "projectStatus", projectStatus);

        ReflectionTestUtils.setField(task, "taskId", 1L);
        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L, "세번째 업무", "테스트 업무");
        TaskRequestDto taskDto = new TaskRequestDto("naht94", 1L, "세번째 업무", "테스트 업무");
        task.save(taskRequestDto);

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
        ReflectionTestUtils.setField(task, "taskId", 1L);
        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L, "세번째 업무", "테스트 업무");
        task.save(taskRequestDto);

        doNothing().when(taskService).deleteTask(anyLong());

        mockMvc.perform(delete("/projects/posts/{task-id}", task.getTaskId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("OK")));

        verify(taskService, times(1)).deleteTask(task.getTaskId());
    }

}