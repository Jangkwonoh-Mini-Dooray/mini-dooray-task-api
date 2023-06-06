package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class DefaultProjectServiceTest {
    @Autowired
    ProjectService projectService;
    @MockBean
    ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("개별 프로젝트 조회 서비스")
    void testGetProject() {

        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("test");
        ProjectDto projectDto = new ProjectDto(1L, projectStatus, "test", "test");

        given(projectRepository.findByProjectId(anyLong()))
                .willReturn(projectDto);

        ProjectDto actual = projectService.getProject(projectDto.getProjectId());

        assertThat(actual.getProjectId()).isEqualTo(projectDto.getProjectId());
        assertThat(actual.getProjectStatus()).isEqualTo(projectDto.getProjectStatus());
        assertThat(actual.getName()).isEqualTo(projectDto.getName());
        assertThat(actual.getDescription()).isEqualTo(projectDto.getDescription());
    }
}