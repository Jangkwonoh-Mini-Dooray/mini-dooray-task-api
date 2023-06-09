package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
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
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Project : Service 테스트")
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
    @DisplayName("Member ID 로 전체 프로젝트 조회 서비스")
    void testGetProjects() {
    }

    @Test
    @Order(2)
    @DisplayName("Project ID 로 개별 프로젝트 조회 서비스")
    void testGetProject() {
        ProjectDto projectDto = new ProjectDto(1L, "test", "test", "test");

        given(projectRepository.findByProjectId(anyLong()))
                .willReturn(projectDto);

        ProjectDto actual = projectService.getProject(projectDto.getProjectId());

        assertThat(actual.getProjectId()).isEqualTo(projectDto.getProjectId());
        assertThat(actual.getProjectStatusName()).isEqualTo(projectDto.getProjectStatusName());
        assertThat(actual.getName()).isEqualTo(projectDto.getName());
        assertThat(actual.getDescription()).isEqualTo(projectDto.getDescription());
    }

    @Test
    @Order(3)
    @DisplayName("프로젝트 생성 서비스")
    void testCreateProject() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto("활성", "test", "test");
        ProjectStatus projectStatus = new ProjectStatus("test");

        given(projectStatusRepository.findById(anyInt()))
                .willReturn(Optional.of(projectStatus));

        Project project = new Project(1L, projectStatus, projectRequestDto.getName(), projectRequestDto.getDescription());

        given(projectRepository.saveAndFlush(any()))
                .willReturn(project);

        ProjectIdDto actual = projectService.createProject(projectRequestDto);
        assertThat(actual.getProjectId()).isEqualTo(project.getProjectId());
    }

    @Test
    @Order(4)
    @DisplayName("프로젝트 수정 서비스")
    void testModifyProject() {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto("휴면", "test", "test");
        ProjectStatus projectStatus = new ProjectStatus("test");
        Project project = new Project(1L, projectStatus, projectRequestDto.getName(), projectRequestDto.getDescription());

        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.of(project));
        given(projectStatusRepository.findById(anyInt()))
                .willReturn(Optional.of(projectStatus));
        given(projectRepository.saveAndFlush(any()))
                .willReturn(project);

        ProjectIdDto actual = projectService.modifyProject(projectRequestDto, project.getProjectId());
        assertThat(actual.getProjectId()).isEqualTo(project.getProjectId());
    }

    @Test
    @Order(5)
    @DisplayName("프로젝트 삭제 서비스")
    void testDeleteProject() {
        ProjectStatus projectStatus = new ProjectStatus("test");
        Project project = new Project(1L, projectStatus, "test", "test");

        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.of(project));
        doNothing().when(projectRepository).deleteById(project.getProjectId());
        projectService.deleteProject(project.getProjectId());
        verify(projectRepository, times(1)).findById(project.getProjectId());
    }

    @Test
    @Order(6)
    @DisplayName("프로젝트 삭제 서비스 : error (프로젝트 ID 가 존재하지 않는 경우)")
    void testFailDeleteProject() {
        doThrow(new NotFoundProjectException(0L)).when(projectRepository).findById(0L);
        assertThrows(NotFoundProjectException.class, () -> projectService.deleteProject(0L));
        verify(projectRepository, times(1)).findById(0L);
        verify(projectRepository, never()).deleteById(anyLong());
    }
}