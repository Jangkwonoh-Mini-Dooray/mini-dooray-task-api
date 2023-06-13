package com.nhnacademy.minidooraytaskapi.projectstatus.repository;

import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("ProjectStatus : Repository 테스트")
class ProjectStatusRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProjectStatusRepository projectStatusRepository;
    @Test
    @DisplayName("프로젝트 상태 테이블 전체 조회")
    void getProjectStatuses() {
        ProjectStatus projectStatus = new ProjectStatus("활성");
        ProjectStatus projectStatus2 = new ProjectStatus("휴면");
        ProjectStatus projectStatus3 = new ProjectStatus("종료");

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(projectStatus2);
        testEntityManager.persist(projectStatus3);

        List<ProjectStatusDto> projectStatuses = projectStatusRepository.getProjectStatuses();

        Assertions.assertThat(projectStatuses).isNotEmpty().hasSize(3);
        Assertions.assertThat(projectStatuses.get(0).getProjectStatusId()).isEqualTo(projectStatus.getProjectStatusId());
    }

    @Test
    @DisplayName("특정 프로젝트 상태 조회")
    void getProjectStatus() {
        ProjectStatus projectStatus = new ProjectStatus("활성");
        ProjectStatus projectStatus2 = new ProjectStatus("휴면");
        ProjectStatus projectStatus3 = new ProjectStatus("종료");

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(projectStatus2);
        testEntityManager.persist(projectStatus3);

        ProjectStatusNameDto projectStatusNameDto = projectStatusRepository.getProjectStatus(projectStatus.getProjectStatusId());

        Assertions.assertThat(projectStatusNameDto.getName()).isEqualTo("활성");
    }
}