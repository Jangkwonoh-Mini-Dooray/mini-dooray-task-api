package com.nhnacademy.minidooraytaskapi.project.repository;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Project : Repository 테스트")
class ProjectRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProjectRepository projectRepository;
    @Test
    @Order(1)
    @DisplayName("Project ID 로 개별 프로젝트 조회")
    void testFindByProjectId() {
        ProjectStatus projectStatus = new ProjectStatus("test");
        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        Project project = new Project(projectStatus, projectRequestDto);
        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);

        ProjectDto actual = projectRepository.findByProjectId(project.getProjectId());
        assertThat(actual.getProjectId()).isEqualTo(project.getProjectId());
    }
}