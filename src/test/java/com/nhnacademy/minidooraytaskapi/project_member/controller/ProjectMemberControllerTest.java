package com.nhnacademy.minidooraytaskapi.project_member.controller;

import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.service.ProjectMemberService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void testGetTargetMembers() throws Exception {
        List<ProjectMemberRequestDto> targetMembers = new ArrayList<>();
        targetMembers.add(new ProjectMemberRequestDto(2, "test1"));
        targetMembers.add(new ProjectMemberRequestDto(2, "test2"));

        when(projectMemberService.getTargetMembers(anyLong()))
                .thenReturn(targetMembers);

        mockMvc.perform(get("/projects/{project-id}/members", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].targetMemberId", equalTo("test1")))
                .andExpect(jsonPath("$[1].targetMemberId", equalTo("test2")));
    }
}