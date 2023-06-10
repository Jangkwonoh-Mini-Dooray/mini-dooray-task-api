package com.nhnacademy.minidooraytaskapi.milestone.controller;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneIdDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneRequestDto;
import com.nhnacademy.minidooraytaskapi.milestone.service.MilestoneService;
import com.nhnacademy.minidooraytaskapi.projectmember.controller.ProjectMemberController;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberDeleteRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.service.ProjectMemberService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MilestoneController.class)
@DisplayName("Milestone : Controller 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MilestoneControllerTest {
    @MockBean
    MilestoneService milestoneService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    @DisplayName("Project ID 로 프로젝트 내 전체 마일스톤 조회")
    void testGetMilestones() throws Exception {
        List<MilestoneDto> milestoneDtoList = new ArrayList<>();
        Long projectId = 1L;
        MilestoneDto milestoneDto = new MilestoneDto(1L, "test", LocalDate.now(), LocalDate.now(), "test");
        milestoneDtoList.add(milestoneDto);
        MilestoneDto milestoneDto2 = new MilestoneDto(2L, "test", LocalDate.now(), LocalDate.now(), "test");
        milestoneDtoList.add(milestoneDto2);

        when(milestoneService.getMilestones(projectId))
                .thenReturn(milestoneDtoList);

        mockMvc.perform(get("/milestones/projects/{project-id}", projectId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].milestoneId", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("test")));
    }

    @Test
    @Order(2)
    @DisplayName("Milestone ID 로 프로젝트 내 특정 마일스톤 조회")
    void getMilestone() throws Exception {
        MilestoneDto milestoneDto = new MilestoneDto(1L, "test", LocalDate.now(), LocalDate.now(), "test");

        when(milestoneService.getMilestone(anyLong()))
                .thenReturn(milestoneDto);

        mockMvc.perform(get("/milestones/{milestone-id}", milestoneDto.getMilestoneId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.milestoneId", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("test")));
    }

    @Test
    @Order(3)
    @DisplayName("마일스톤 생성")
    void testCreateMilestone() throws Exception {
        Long projectId = 1L;
        MilestoneRequestDto requestDto = new MilestoneRequestDto("test", LocalDate.now(), LocalDate.now(), "test");
        MilestoneIdDto milestoneIdDto = new MilestoneIdDto(1L);

        when(milestoneService.createMilestone(projectId, requestDto)).thenReturn(milestoneIdDto);

        mockMvc.perform(post("/milestones/projects/{project-id}", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("마일스톤 수정")
    void testModifyMilestone() throws Exception {
        Long milestoneId = 1L;
        MilestoneRequestDto requestDto = new MilestoneRequestDto("test", LocalDate.now(), LocalDate.now(), "test");
        MilestoneIdDto milestoneIdDto = new MilestoneIdDto(milestoneId);

        when(milestoneService.modifyMilestone(milestoneId, requestDto)).thenReturn(milestoneIdDto);
        mockMvc.perform(put("/milestones/{milestone-id}", milestoneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    @DisplayName("마일스톤 삭제")
    void testDeleteMilestone() throws Exception {
        Long milestoneId = 1L;

        mockMvc.perform(delete("/milestones/{milestone-id}", milestoneId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("OK")));

        verify(milestoneService).deleteMilestone(milestoneId);
    }
}