package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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
    @DisplayName("개별 프로젝트 조회 서비스")
    void testGetProject() {
        Project project = new Project();
        project.setName("test");
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("test");
        project.setProjectStatus(projectStatus);

        given(projectRepository.getAllByProjectId(anyLong()))
                .willReturn(new ProjectDto() {
                    @Override
                    public Long getProjectId() {
                        return project.getProjectId();
                    }

                    @Override
                    public ProjectStatus getProjectStatus() {
                        return project.getProjectStatus();
                    }

                    @Override
                    public String getName() {
                        return project.getName();
                    }

                    @Override
                    public String getDescription() {
                        return project.getDescription();
                    }
                });

        ProjectDto actual = projectService.getProject(project.getProjectId());
    }
}