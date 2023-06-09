package com.nhnacademy.minidooraytaskapi.project_member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberDeleteRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.service.ProjectMemberService;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ProjectMemberController.class)
@DisplayName("ProjectMember : Controller 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectMemberControllerTest {
    @MockBean
    ProjectMemberService projectMemberService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    @DisplayName("Project ID 로 프로젝트 멤버 조회")
    void testGetProjectMembers() throws Exception {
        List<ProjectMemberRequestDto> targetMembers = new ArrayList<>();
        targetMembers.add(new ProjectMemberRequestDto(2, "test1"));
        targetMembers.add(new ProjectMemberRequestDto(2, "test2"));

        when(projectMemberService.getProjectMembers(anyLong()))
                .thenReturn(targetMembers);

        mockMvc.perform(get("/projects/{project-id}/members", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].targetMemberId", equalTo("test1")))
                .andExpect(jsonPath("$[1].targetMemberId", equalTo("test2")));
    }

    @Test
    @Order(2)
    @DisplayName("프로젝트 멤버 초대")
    void testAddProjectMembers() throws Exception {
        Long projectId = 1L;
        List<ProjectMemberRequestDto> projectMemberRequestDtoList = new ArrayList<>();
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "test1"));
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "test2"));

        mockMvc.perform(post("/projects/{project-id}/members", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectMemberRequestDtoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("OK")));
    }

    @Test
    @Order(3)
    @DisplayName("프로젝트 멤버 수정")
    void testModifyProjectMembers() throws Exception {
        Long projectId = 1L;
        List<ProjectMemberRequestDto> projectMemberRequestDtoList = new ArrayList<>();
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "test1"));
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "test2"));

        mockMvc.perform(put("/projects/{project-id}/members", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectMemberRequestDtoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("OK")));
    }

    @Test
    @Order(4)
    @DisplayName("프로젝트 멤버 삭제")
    void testDeleteProjectMembers() throws Exception {
        Long projectId = 1L;
        List<ProjectMemberDeleteRequestDto> projectMemberDeleteRequestDtoList = new ArrayList<>();
        projectMemberDeleteRequestDtoList.add(new ProjectMemberDeleteRequestDto("test"));

        mockMvc.perform(delete("/projects/{project-id}/members", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projectMemberDeleteRequestDtoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("OK")));
    }
}