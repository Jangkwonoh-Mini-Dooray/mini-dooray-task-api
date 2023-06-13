package com.nhnacademy.minidooraytaskapi.projectstatus.service;

import com.nhnacademy.minidooraytaskapi.exception.DuplicateIntIdException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectStatusException;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusIdDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.projectstatus.repository.ProjectStatusRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProjectStatus : Service 테스트")
class ProjectStatusServiceTest {
    @InjectMocks
    DefaultProjectStatusService projectStatusService;
    @Mock
    ProjectStatusRepository projectStatusRepository;

    @Test
    @DisplayName("프로젝트 상태 데이터 전부 가져오는 Service")
    void getProjectStatuses() {
        ProjectStatusDto projectStatus = new ProjectStatusDto();
        ProjectStatusDto projectStatus2 = new ProjectStatusDto();
        projectStatus.setProjectStatusId(1);
        projectStatus2.setProjectStatusId(2);
        projectStatus.setName("활성");
        projectStatus2.setName("휴면");
        given(projectStatusRepository.getProjectStatuses()).willReturn(List.of(projectStatus, projectStatus2));

        List<ProjectStatusDto> projectStatuses = projectStatusService.getProjectStatuses();

        Assertions.assertThat(projectStatuses).hasSize(2);
        Assertions.assertThat(projectStatuses.get(0).getName()).isEqualTo("활성");
    }

    @Test
    @DisplayName("특정 프로젝트 상태 데이터 가져오는 Service")
    void getProjectStatus() {
        ProjectStatusNameDto projectStatus = new ProjectStatusNameDto();
        ProjectStatusNameDto projectStatus2 = new ProjectStatusNameDto();
        projectStatus.setName("활성");
        projectStatus2.setName("휴면");
        given(projectStatusRepository.getProjectStatus(anyInt())).willReturn(projectStatus);

        ProjectStatusNameDto result = projectStatusService.getProjectStatus(1);

        Assertions.assertThat(result.getName()).isEqualTo("활성");
    }

    @Test
    @DisplayName("상태 데이터 생성 Service #실패 id 중복")
    void createProjectStatus() {
        ProjectStatusDto projectStatusDto = new ProjectStatusDto();
        given(projectStatusRepository.existsById(anyInt())).willReturn(true);

        assertThrows(DuplicateIntIdException.class, () -> projectStatusService.createProjectStatus(projectStatusDto));
    }

    @Test
    @DisplayName("상태 데이터 생성 Service #성공")
    void createProjectStatus2() {
        ProjectStatusDto projectStatusDto = new ProjectStatusDto();
        projectStatusDto.setProjectStatusId(4);
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setProjectStatusId(4);
        given(projectStatusRepository.existsById(anyInt())).willReturn(false);
        given(projectStatusRepository.saveAndFlush(any())).willReturn(projectStatus);
        ProjectStatusIdDto projectStatusIdDto = projectStatusService.createProjectStatus(projectStatusDto);
        Assertions.assertThat(projectStatusIdDto.getProjectStatusId()).isEqualTo(4);
    }

    @Test
    @DisplayName("상태 데이터 업데이트 Service # 실패")
    void updateMember() {
        given(projectStatusRepository.findById(anyInt())).willReturn(Optional.empty());
        assertThrows(NotFoundProjectStatusException.class, () -> projectStatusService.updateMember(1, new ProjectStatusNameDto()));
    }

    @Test
    @DisplayName("상태 데이터 업데이트 Service # 성공")
    void updateMember2() {
        ProjectStatus projectStatus = new ProjectStatus();
        ReflectionTestUtils.setField(projectStatus, "projectStatusId", 1);
        given(projectStatusRepository.findById(anyInt())).willReturn(Optional.of(projectStatus));
        given(projectStatusRepository.saveAndFlush(any())).willReturn(projectStatus);

        ProjectStatusIdDto projectStatusIdDto = projectStatusService.updateMember(1, new ProjectStatusNameDto());

        Assertions.assertThat(projectStatusIdDto.getProjectStatusId()).isEqualTo(1);
    }

    @Test
    @DisplayName("상태 데이터 삭제 Service #실패")
    void deleteProjectStatus() {
        given(projectStatusRepository.existsById(anyInt())).willReturn(false);
        assertThrows(NotFoundProjectStatusException.class, () -> projectStatusService.deleteProjectStatus(1));
    }

    @Test
    @DisplayName("상태 데이터 삭제 Service #성공")
    void deleteProjectStatus2() {
        given(projectStatusRepository.existsById(anyInt())).willReturn(true);
        doNothing().when(projectStatusRepository).deleteById(anyInt());
        projectStatusService.deleteProjectStatus(1);
        verify(projectStatusRepository, times(1)).deleteById(anyInt());
    }
}