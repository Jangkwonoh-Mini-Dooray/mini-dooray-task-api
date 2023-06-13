package com.nhnacademy.minidooraytaskapi.projectstatus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusIdDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.service.ProjectStatusService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectStatusController.class)
@DisplayName("ProjectStatus : Controller 테스트")
class ProjectStatusControllerTest {
    @MockBean
    ProjectStatusService projectStatusService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("projectStatus 데이터 전부 가져오는 Controller")
    void getProjectStatuses() throws Exception {
        ProjectStatusDto projectStatus = new ProjectStatusDto();
        ProjectStatusDto projectStatus2 = new ProjectStatusDto();
        projectStatus.setProjectStatusId(1);
        projectStatus2.setProjectStatusId(2);
        projectStatus.setName("활성");
        projectStatus2.setName("휴면");
        when(projectStatusService.getProjectStatuses()).thenReturn(List.of(projectStatus, projectStatus2));
        mockMvc.perform(get("/project-status"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].projectStatusId", equalTo(1)));

    }

    @Test
    @DisplayName("projectStatus 특정 데이터 가져오는 Controller")
    void getProjectStatus() throws Exception {
        ProjectStatusNameDto projectStatusNameDto = new ProjectStatusNameDto();
        projectStatusNameDto.setName("test");
        when(projectStatusService.getProjectStatus(anyInt())).thenReturn(projectStatusNameDto);
        mockMvc.perform(get("/project-status/{projectStatusId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("test")));
    }

    @Test
    @DisplayName("projectStatus 데이터 생성하는 Controller #살패")
    void createProjectStatus() throws Exception {
        mockMvc.perform(post("/project-status"))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("projectStatus 데이터 생성하는 Controller #성공")
    void createProjectStatus2() throws Exception {
        ProjectStatusDto projectStatusDto = new ProjectStatusDto();
        projectStatusDto.setProjectStatusId(1);
        projectStatusDto.setName("test");
        ProjectStatusIdDto projectStatusIdDto = new ProjectStatusIdDto();
        projectStatusIdDto.setProjectStatusId(1);
        when(projectStatusService.createProjectStatus(any())).thenReturn(projectStatusIdDto);
        mockMvc.perform(post("/project-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectStatusDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectStatusId", equalTo(1)));
    }

    @Test
    @DisplayName("projectStatus 데이터 수정하는 Controller #실패")
    void updateProjectStatus() throws Exception {
        mockMvc.perform(put("/project-status/{projectStatusId}", 1))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("projectStatus 데이터 수정하는 Controller #성공")
    void updateProjectStatus2() throws Exception {
        ProjectStatusDto projectStatusDto = new ProjectStatusDto();
        projectStatusDto.setProjectStatusId(1);
        projectStatusDto.setName("test");
        ProjectStatusIdDto projectStatusIdDto = new ProjectStatusIdDto();
        projectStatusIdDto.setProjectStatusId(1);
        when(projectStatusService.updateProjectStatus(anyInt(),any())).thenReturn(projectStatusIdDto);
        mockMvc.perform(put("/project-status/{projectStatusId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(projectStatusDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectStatusId", equalTo(1)));
    }

    @Test
    @DisplayName("projectStatus 데이터 삭제하는 Controller")
    void deleteProjectStatus() throws Exception {
        doNothing().when(projectStatusService).deleteProjectStatus(anyInt());
        mockMvc.perform(delete("/project-status/{projectStatusId}", 1))
                .andExpect(status().isNoContent());
    }
}