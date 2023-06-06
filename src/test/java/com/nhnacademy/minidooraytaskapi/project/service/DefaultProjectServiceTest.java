package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.project_status.repository.ProjectStatusRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("프로젝트 서비스 테스트")
class DefaultProjectServiceTest {
    @Autowired
    ProjectService projectService;
    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    ProjectStatusRepository projectStatusRepository;

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

    @Test
    @Order(2)
    @DisplayName("프로젝트 생성 서비스")
    void testCreateProject() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto("활성", "test", "test");

        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("test");
        given(projectStatusRepository.findById(anyInt()))
                .willReturn(Optional.of(projectStatus));

        Project project = new Project();
        project.setProjectId(1L);
        project.setProjectStatus(projectStatus);
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());

        given(projectRepository.saveAndFlush(any()))
                .willReturn(project);

        ProjectIdDto actual = projectService.createProject(projectRequestDto);
        assertThat(actual.getProjectId()).isEqualTo(project.getProjectId());
    }
}