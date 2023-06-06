//package com.nhnacademy.minidooraytaskapi.project.controller;
//
//import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
//import com.nhnacademy.minidooraytaskapi.project.entity.Project;
//import com.nhnacademy.minidooraytaskapi.project.service.ProjectService;
//import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
//import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
//import com.nhnacademy.minidooraytaskapi.task.entity.Task;
//import com.nhnacademy.minidooraytaskapi.task.service.TaskService;
//import org.junit.jupiter.api.*;
//import static org.mockito.BDDMockito.given;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class ProjectControllerTest {
//    @MockBean
//    ProjectService projectService;
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    @DisplayName("개별 프로젝트 조회")
//    void testGetProject() throws Exception {
////        ProjectStatus projectStatus = new ProjectStatus();
////        projectStatus.setName("test");
////
////        ProjectDto projectDto = new ProjectDto(1L, projectStatus, "test", "test");
////
////        given(projectService.getProject(anyLong()))
////                .willReturn(projectDto);
////
////        mockMvc.perform(get("/projects/{project-id}", 1L))
////                .andExpect(status().isOk())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
//}