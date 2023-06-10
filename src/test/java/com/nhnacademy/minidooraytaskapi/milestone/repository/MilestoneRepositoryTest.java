package com.nhnacademy.minidooraytaskapi.milestone.repository;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Milestone : Repository 테스트")
class MilestoneRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    MilestoneRepository milestoneRepository;

    @Test
    @Order(1)
    @DisplayName("프로젝트 내 마일스톤 전체 조회")
    void testFindMilestones() {
        ProjectStatus projectStatus = new ProjectStatus("test");
        Project project = new Project(projectStatus, "test", "test");

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);

        Milestone milestone = new Milestone("test", LocalDate.now(), LocalDate.now(), "test", project);
        testEntityManager.persist(milestone);

        List<MilestoneDto> milestones = milestoneRepository.findMilestones(project.getProjectId());
        assertEquals(1, milestones.size());

        MilestoneDto milestoneDto = milestones.get(0);
        assertEquals(milestone.getMilestoneId(), milestoneDto.getMilestoneId());
        assertEquals(milestone.getName(), milestoneDto.getName());
        assertEquals(milestone.getStartPeriod(), milestoneDto.getStartPeriod());
        assertEquals(milestone.getEndPeriod(), milestoneDto.getEndPeriod());
        assertEquals(milestone.getStatus(), milestoneDto.getStatus());
    }

    @Test
    @Order(2)
    @DisplayName("프로젝트 내 특정 마일스톤 조회")
    void testFindMilestone() {
        ProjectStatus projectStatus = new ProjectStatus("test");
        Project project = new Project(projectStatus, "test", "test");

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);

        Milestone milestone = new Milestone("test", LocalDate.now(), LocalDate.now(), "test", project);
        testEntityManager.persist(milestone);

        MilestoneDto milestoneDto = milestoneRepository.findMilestone(milestone.getMilestoneId());
        assertEquals(milestone.getMilestoneId(), milestoneDto.getMilestoneId());
        assertEquals(milestone.getName(), milestoneDto.getName());
        assertEquals(milestone.getStartPeriod(), milestoneDto.getStartPeriod());
        assertEquals(milestone.getEndPeriod(), milestoneDto.getEndPeriod());
        assertEquals(milestone.getStatus(), milestoneDto.getStatus());
    }
}