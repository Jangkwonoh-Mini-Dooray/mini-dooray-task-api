package com.nhnacademy.minidooraytaskapi.project.repository;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class ProjectRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProjectRepository projectRepository;

    @Test
    @Order(1)
    @DisplayName("개별 프로젝트 조회")
    void testFindByProjectId() {
        Project project = new Project();
        project.setName("test");
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("test");
        project.setProjectStatus(projectStatus);

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);

        ProjectDto actual = projectRepository.findByProjectId(project.getProjectId());
        assertThat(actual.getProjectId()).isEqualTo(project.getProjectId());
    }
}