package com.nhnacademy.minidooraytaskapi.project_member.repository;

import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.entity.ProjectMember;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project_authority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("GetProject : Repository 테스트")
class ProjectMemberRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProjectMemberRepository projectMemberRepository;

    @Test
    @Order(1)
    @DisplayName("Project ID 로 프로젝트 멤버 조회")
    void testFindTargetMembers() {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("test");
        ProjectAuthority projectAuthority = new ProjectAuthority();
        projectAuthority.setName("test");
        Project project = new Project(projectStatus, "test", "test");
        ProjectMember projectMember = new ProjectMember(new ProjectMember.Pk("test", project.getProjectId()), project, projectAuthority);

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(projectAuthority);
        testEntityManager.persist(project);
        testEntityManager.persist(projectMember);

        List<ProjectMemberRequestDto> actual = projectMemberRepository.findTargetMembers(project.getProjectId());
        assertThat(actual.size()).isEqualTo(1);
    }
}