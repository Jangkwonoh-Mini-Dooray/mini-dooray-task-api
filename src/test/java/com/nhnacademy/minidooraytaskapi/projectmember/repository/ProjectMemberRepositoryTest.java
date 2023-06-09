package com.nhnacademy.minidooraytaskapi.projectmember.repository;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberResponseDto;
import com.nhnacademy.minidooraytaskapi.projectmember.entity.ProjectMember;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
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
@DisplayName("ProjectMember : Repository 테스트")
class ProjectMemberRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProjectMemberRepository projectMemberRepository;

    @Test
    @Order(1)
    @DisplayName("Project ID 로 프로젝트 멤버 조회")
    void testFindTargetMembers() {
        ProjectStatus projectStatus = new ProjectStatus("test");
        ProjectAuthority projectAuthority = new ProjectAuthority();
        ProjectRequestDto projectRequestDto = new ProjectRequestDto("test", "test", "test");
        Project project = new Project(projectStatus, projectRequestDto);
        ProjectMember projectMember = new ProjectMember(new ProjectMember.Pk("test", project.getProjectId()), project, projectAuthority);

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(projectAuthority);
        testEntityManager.persist(project);
        testEntityManager.persist(projectMember);

        Project resultProject = projectMember.getProject();
        ProjectAuthority resultProjectAuthority = projectMember.getProjectAuthority();
        List<ProjectMemberResponseDto> actual = projectMemberRepository.findProjectMembers(resultProject.getProjectId());
        assertThat(actual.size()).isEqualTo(1);
        assertThat(resultProjectAuthority.getProjectAuthorityId()).isEqualTo(projectAuthority.getProjectAuthorityId());
    }
}