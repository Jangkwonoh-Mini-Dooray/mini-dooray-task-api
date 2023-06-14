package com.nhnacademy.minidooraytaskapi.projectauthority.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityIdDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.service.ProjectAuthorityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectAuthorityController.class)
@DisplayName("ProjectAuthority : Controller 테스트")
class ProjectAuthorityControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ProjectAuthorityService projectAuthorityService;
    @Test
    @DisplayName("ProjectAuthority 목록 가져오는 Controller")
    void getProjectAuthorities() throws Exception {
        ProjectAuthorityDto projectAuthorityDto = new ProjectAuthorityDto(1, "hi");
        ProjectAuthorityDto projectAuthorityDto2 = new ProjectAuthorityDto(2, "hello");

        when(projectAuthorityService.getProjectAuthorities())
                .thenReturn(List.of(projectAuthorityDto, projectAuthorityDto2));

        mockMvc.perform(get("/project-authority"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].projectAuthorityId", equalTo(1)));
    }

    @Test
    @DisplayName("특정 ProjectAuthority 가져오는 Controller")
    void getProjectAuthority() throws Exception {
        ProjectAuthorityNameDto projectAuthorityNameDto = new ProjectAuthorityNameDto("hi");

        when(projectAuthorityService.getProjectAuthority(anyInt()))
                .thenReturn(projectAuthorityNameDto);

        mockMvc.perform(get("/project-authority/{project-authority-id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("hi")));
    }

    @Test
    @DisplayName("ProjectAuthority 생성하는 Controller #실패")
    void postProjectAuthority() throws Exception {
        mockMvc.perform(post("/project-authority"))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("ProjectAuthority 생성하는 Controller #성공")
    void postProjectAuthority2() throws Exception {
        ProjectAuthorityIdDto projectAuthorityIdDto = new ProjectAuthorityIdDto(1);
        ProjectAuthorityDto projectAuthorityDto = new ProjectAuthorityDto(1, "test");
        when(projectAuthorityService.postProjectAuthority(any()))
                .thenReturn(projectAuthorityIdDto);

        mockMvc.perform(post("/project-authority")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectAuthorityDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectAuthorityId", equalTo(1)));
    }

    @Test
    @DisplayName("ProjectAuthority 수정하는 Controller #실패")
    void putProjectAuthority() throws Exception {
        mockMvc.perform(put("/project-authority/{project-authority-id}", 1))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("ProjectAuthority 수정하는 Controller #성공")
    void putProjectAuthority2() throws Exception {
        ProjectAuthorityIdDto projectAuthorityIdDto = new ProjectAuthorityIdDto(1);
        ProjectAuthorityDto projectAuthorityDto = new ProjectAuthorityDto(1, "test");
        when(projectAuthorityService.putProjectAuthority(any(), anyInt()))
                .thenReturn(projectAuthorityIdDto);

        mockMvc.perform(put("/project-authority/{project-authority-id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectAuthorityDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectAuthorityId", equalTo(1)));
    }

    @Test
    @DisplayName("ProjectAuthority 지우는 Controller")
    void deleteProjectAuthority() throws Exception {
        mockMvc.perform(delete("/project-authority/{project-authority-id}", 1))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("OK")));
    }
}