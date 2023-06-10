package com.nhnacademy.minidooraytaskapi.project.controller;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.service.ProjectService;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.equalTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
@DisplayName("Project : Controller 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectControllerTest {
    @MockBean
    ProjectService projectService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    @DisplayName("전체 프로젝트 조회")
    void testGetProjects() throws Exception {
        List<Project> projectList = new ArrayList<>();
        ProjectStatus projectStatus = new ProjectStatus("test");
        Project project = new Project(projectStatus, "test", "test");
        projectList.add(project);

        when(projectService.getProjects())
                .thenReturn(projectList);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    @DisplayName("개별 프로젝트 조회")
    void testGetProject() throws Exception {
        ProjectDto projectDto = new ProjectDto(1L, "test", "test", "test");

        when(projectService.getProject(anyLong()))
                .thenReturn(projectDto);

        mockMvc.perform(get("/projects/{project-id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    @DisplayName("프로젝트 생성")
    void testCreateProject() throws Exception {
        ProjectStatus projectStatus = new ProjectStatus("test");
        ProjectRequestDto projectRequestDto = new ProjectRequestDto(projectStatus.getName(), "test", "test");
        Project project = new Project(1L, projectStatus, projectRequestDto.getName(), projectRequestDto.getDescription());

        when(projectService.createProject(any()))
                .thenReturn(new ProjectIdDto(1L));

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectId", equalTo(1)));
    }

    @Test
    @Order(4)
    @DisplayName("프로젝트 생성 : error (RequestBody 가 없는 경우)")
    void testFailCreateProject() throws Exception {
        when(projectService.createProject(any()))
                .thenReturn(new ProjectIdDto(1L));

        mockMvc.perform(post("/projects"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    @DisplayName("프로젝트 수정")
    void testModifyProject() throws Exception {
        ProjectStatus projectStatus = new ProjectStatus("test");
        ProjectRequestDto projectRequestDto = new ProjectRequestDto(projectStatus.getName(), "test", "test");
        Project project = new Project(1L, projectStatus, projectRequestDto.getName(), projectRequestDto.getDescription());

        when(projectService.modifyProject(any(), anyLong()))
                .thenReturn(new ProjectIdDto(1L));

        mockMvc.perform(put("/projects/{project-id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectId", equalTo(1)));
    }

    @Test
    @Order(6)
    @DisplayName("프로젝트 삭제")
    void testDeleteProject() throws Exception {
        mockMvc.perform(delete("/projects/{project-id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("ok")));

        verify(projectService, times(1)).deleteProject(1L);
    }
}