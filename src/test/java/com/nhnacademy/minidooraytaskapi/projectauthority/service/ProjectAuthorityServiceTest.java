package com.nhnacademy.minidooraytaskapi.projectauthority.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectAuthorityException;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityIdDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.projectauthority.repository.ProjectAuthorityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

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
@DisplayName("ProjectAuthority : Service 테스트")
class ProjectAuthorityServiceTest {
    @InjectMocks
    DefaultProjectAuthorityService projectAuthorityService;
    @Mock
    ProjectAuthorityRepository projectAuthorityRepository;

    @Test
    @DisplayName("ProjectAuthority 테이블에 존재하는 모든 데이터를 가져오는 Service")
    void getProjectAuthorities() {
        ProjectAuthorityDto projectAuthorityDto = new ProjectAuthorityDto(1, "hi");
        ProjectAuthorityDto projectAuthorityDto2 = new ProjectAuthorityDto(2, "hello");

        when(projectAuthorityRepository.getProjectAuthorities())
                .thenReturn(List.of(projectAuthorityDto, projectAuthorityDto2));

        List<ProjectAuthorityDto> projectAuthorities = projectAuthorityService.getProjectAuthorities();

        Assertions.assertThat(projectAuthorities).hasSize(2);
        Assertions.assertThat(projectAuthorities.get(0).getProjectAuthorityId()).isEqualTo(1);
    }

    @Test
    @DisplayName("ProjectAuthority 테이블에 특정 데이터를 가져오는 Service")
    void getProjectAuthority() {
        ProjectAuthorityNameDto projectAuthorityNameDto = new ProjectAuthorityNameDto("hi");

        when(projectAuthorityRepository.getProjectAuthority(anyInt()))
                .thenReturn(projectAuthorityNameDto);

        ProjectAuthorityNameDto projectAuthority = projectAuthorityService.getProjectAuthority(1);

        Assertions.assertThat(projectAuthority.getName()).isEqualTo("hi");
    }

    @Test
    @DisplayName("ProjectAuthority 데이터 생성")
    void postProjectAuthority() {
        ProjectAuthority projectAuthority = new ProjectAuthority();
        ReflectionTestUtils.setField(projectAuthority, "projectAuthorityId", 4);
        projectAuthority.setName("test");
        ProjectAuthorityDto projectAuthorityDto = new ProjectAuthorityDto();

        when(projectAuthorityRepository.saveAndFlush(any())).thenReturn(projectAuthority);

        ProjectAuthorityIdDto projectAuthorityIdDto = projectAuthorityService.postProjectAuthority(projectAuthorityDto);

        Assertions.assertThat(projectAuthorityIdDto.getProjectAuthorityId()).isEqualTo(4);
    }

    @Test
    @DisplayName("ProjectAuthority 데이터 수정 #실패")
    void putProjectAuthority() {
        ProjectAuthorityDto projectAuthorityDto = new ProjectAuthorityDto();

        when(projectAuthorityRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundProjectAuthorityException.class, () -> projectAuthorityService.putProjectAuthority(projectAuthorityDto, 1));
    }

    @Test
    @DisplayName("ProjectAuthority 데이터 수정 #실패")
    void putProjectAuthority2() {
        ProjectAuthorityDto projectAuthorityDto = new ProjectAuthorityDto();
        ProjectAuthority projectAuthority = new ProjectAuthority();
        ReflectionTestUtils.setField(projectAuthority, "projectAuthorityId", 4);

        when(projectAuthorityRepository.findById(anyInt())).thenReturn(Optional.of(projectAuthority));
        when(projectAuthorityRepository.saveAndFlush(any())).thenReturn(projectAuthority);

        ProjectAuthorityIdDto projectAuthorityIdDto = projectAuthorityService.putProjectAuthority(projectAuthorityDto, 4);
        Assertions.assertThat(projectAuthorityIdDto.getProjectAuthorityId()).isEqualTo(4);
    }
    @Test
    @DisplayName("ProjectAuthority 데이터 삭제 #실패")
    void deleteProjectAuthority() {
        when(projectAuthorityRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(NotFoundProjectAuthorityException.class, () -> projectAuthorityService.deleteProjectAuthority(1));
    }

    @Test
    @DisplayName("ProjectAuthority 데이터 삭제 #성공")
    void deleteProjectAuthority2() {
        when(projectAuthorityRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(projectAuthorityRepository).deleteById(anyInt());

        projectAuthorityService.deleteProjectAuthority(1);
        verify(projectAuthorityRepository, times(1)).deleteById(1);
    }
}