package com.nhnacademy.minidooraytaskapi.milestone.service;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneIdDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneRequestDto;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.milestone.repository.MilestoneRepository;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("milestone : Service 테스트")
class DefaultMilestoneServiceTest {
    @InjectMocks
    DefaultMilestoneService milestoneService;
    @Mock
    MilestoneRepository milestoneRepository;
    @Mock
    ProjectRepository projectRepository;

    @Test
    @Order(1)
    @DisplayName("Project ID 로 프로젝트 내 전체 마일스톤 조회 서비스")
    void testGetMilestones() {
        List<MilestoneDto> milestoneDtoList = new ArrayList<>();
        Long projectId = 1L;
        MilestoneDto milestoneDto = new MilestoneDto(1L, "test", LocalDate.now(), LocalDate.now(), "test");
        milestoneDtoList.add(milestoneDto);
        MilestoneDto milestoneDto2 = new MilestoneDto(2L, "test", LocalDate.now(), LocalDate.now(), "test");
        milestoneDtoList.add(milestoneDto2);

        given(projectRepository.existsById(projectId))
                .willReturn(true);
        given(milestoneRepository.findMilestones(projectId))
                .willReturn(milestoneDtoList);

        List<MilestoneDto> actual = milestoneService.getMilestones(projectId);

        assertThat(actual).hasSize(2);
        assertThat(actual.get(0).getMilestoneId()).isEqualTo(milestoneDto.getMilestoneId());
        assertThat(actual.get(0).getName()).isEqualTo(milestoneDto.getName());
        assertThat(actual.get(0).getStartPeriod()).isEqualTo(milestoneDto.getStartPeriod());
        assertThat(actual.get(0).getEndPeriod()).isEqualTo(milestoneDto.getEndPeriod());
        assertThat(actual.get(0).getStatus()).isEqualTo(milestoneDto.getStatus());

        assertThat(actual.get(1).getMilestoneId()).isEqualTo(milestoneDto2.getMilestoneId());
        assertThat(actual.get(1).getName()).isEqualTo(milestoneDto2.getName());
        assertThat(actual.get(1).getStartPeriod()).isEqualTo(milestoneDto2.getStartPeriod());
        assertThat(actual.get(1).getEndPeriod()).isEqualTo(milestoneDto2.getEndPeriod());
        assertThat(actual.get(1).getStatus()).isEqualTo(milestoneDto2.getStatus());
    }

    @Test
    @Order(2)
    @DisplayName("Milestone ID 로 프로젝트 내 특정 마일스톤 조회 서비스")
    void testGetMilestone() {
        Long projectId = 1L;
        MilestoneDto milestoneDto = new MilestoneDto(1L, "test", LocalDate.now(), LocalDate.now(), "test");

        given(milestoneRepository.existsById(projectId))
                .willReturn(true);
        given(milestoneRepository.findMilestone(projectId))
                .willReturn(milestoneDto);

        MilestoneDto actual = milestoneService.getMilestone(projectId);

        assertThat(actual.getMilestoneId()).isEqualTo(milestoneDto.getMilestoneId());
        assertThat(actual.getName()).isEqualTo(milestoneDto.getName());
        assertThat(actual.getStartPeriod()).isEqualTo(milestoneDto.getStartPeriod());
        assertThat(actual.getEndPeriod()).isEqualTo(milestoneDto.getEndPeriod());
        assertThat(actual.getStatus()).isEqualTo(milestoneDto.getStatus());
    }

    @Test
    @Order(3)
    @DisplayName("마일스톤 생성")
    void testCreateMilestone() {
        Long projectId = 1L;
        MilestoneRequestDto requestDto = new MilestoneRequestDto("test", LocalDate.now(), LocalDate.now(), "test");
        Project project = new Project();
        Milestone milestone = new Milestone(requestDto, project);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(milestoneRepository.saveAndFlush(any(Milestone.class))).thenReturn(milestone);

        MilestoneIdDto actual = milestoneService.createMilestone(projectId, requestDto);

        assertThat(actual.getMilestoneId()).isEqualTo(milestone.getMilestoneId());
        verify(projectRepository).findById(projectId);
        verify(milestoneRepository).saveAndFlush(any(Milestone.class));
    }

    @Test
    @Order(4)
    @DisplayName("마일스톤 수정")
    void testModifyMilestone() {
        Long milestoneId = 1L;
        MilestoneRequestDto requestDto = new MilestoneRequestDto("test", LocalDate.now(), LocalDate.now(), "test");
        Milestone milestone = new Milestone(requestDto, new Project());

        when(milestoneRepository.findById(milestoneId)).thenReturn(Optional.of(milestone));
        when(milestoneRepository.saveAndFlush(any(Milestone.class))).thenReturn(milestone);

        MilestoneIdDto actual = milestoneService.modifyMilestone(milestoneId, requestDto);

        assertThat(actual.getMilestoneId()).isEqualTo(milestone.getMilestoneId());
        verify(milestoneRepository).findById(milestoneId);
        verify(milestoneRepository).saveAndFlush(any(Milestone.class));
    }

    @Test
    @Order(5)
    @DisplayName("마일스톤 삭제")
    void testDeleteMilestone() {
        Long milestoneId = 1L;

        when(milestoneRepository.existsById(milestoneId)).thenReturn(true);

        assertDoesNotThrow(() -> milestoneService.deleteMilestone(milestoneId));
        verify(milestoneRepository).existsById(milestoneId);
        verify(milestoneRepository).deleteById(milestoneId);
    }
}