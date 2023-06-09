package com.nhnacademy.minidooraytaskapi.project_member.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectAuthorityException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectMemberException;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.project_authority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.project_authority.repository.ProjectAuthorityRepository;
import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.entity.ProjectMember;
import com.nhnacademy.minidooraytaskapi.project_member.repository.ProjectMemberRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("ProjectMember : Service 테스트")
class DefaultProjectMemberServiceTest {
    @Autowired
    ProjectMemberService projectMemberService;
    @MockBean
    ProjectMemberRepository projectMemberRepository;
    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    ProjectAuthorityRepository projectAuthorityRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("Project ID 로 프로젝트 멤버 조회 서비스")
    void testGetTargetMembers() {
        List<ProjectMemberRequestDto> projectMemberRequestDtoList = new ArrayList<>();
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "test1"));
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "test2"));

        given(projectMemberRepository.findProjectMembers(anyLong()))
                .willReturn(projectMemberRequestDtoList);

        List<ProjectMemberRequestDto> actual = projectMemberService.getProjectMembers(1L);

        assertThat(actual).hasSize(2);
        assertThat(actual.get(0).getTargetMemberId()).isEqualTo("test1");
        assertThat(actual.get(1).getTargetMemberId()).isEqualTo("test2");
    }

    @Test
    @Order(2)
    @DisplayName("프로젝트 멤버 초대 서비스")
    void testAddTargetMembers() {
        List<ProjectMemberRequestDto> projectMemberRequestDtoList = new ArrayList<>();
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "suebin"));
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "sudoli"));

        ProjectMember projectMember1 = new ProjectMember();
        ProjectMember projectMember2 = new ProjectMember();
        List<ProjectMember> projectMembers = Arrays.asList(projectMember1, projectMember2);

        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.of(new Project()));
        given(projectAuthorityRepository.findById(anyInt()))
                .willReturn(Optional.of(new ProjectAuthority()));
        given(projectMemberRepository.saveAllAndFlush(anyList()))
                .willReturn(projectMembers);

        projectMemberService.addProjectMembers(1L, projectMemberRequestDtoList);

        verify(projectMemberRepository).saveAllAndFlush(anyList());
        ArgumentCaptor<List<ProjectMember>> projectMembersCaptor = ArgumentCaptor.forClass(List.class);
        verify(projectMemberRepository).saveAllAndFlush(projectMembersCaptor.capture());

        List<ProjectMember> savedProjectMembers = projectMembersCaptor.getValue();
        assertThat(savedProjectMembers).hasSize(2);
    }

    @Test
    @Order(3)
    @DisplayName("프로젝트 멤버 초대 서비스 실패 : 프로젝트나 권한을 찾을 수 없는 경우")
    void testAddTargetMembersFail() {
        List<ProjectMemberRequestDto> projectMemberRequestDtoList = new ArrayList<>();
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "suebin"));
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "sudoli"));

        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        Assertions.assertThrows(NotFoundProjectException.class, () -> {
            projectMemberService.addProjectMembers(1L, projectMemberRequestDtoList);
        });

        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.of(new Project()));
        given(projectAuthorityRepository.findById(anyInt()))
                .willReturn(Optional.empty());

        Assertions.assertThrows(NotFoundProjectAuthorityException.class, () -> {
            projectMemberService.addProjectMembers(1L, projectMemberRequestDtoList);
        });
    }

    @Test
    @Order(4)
    @DisplayName("프로젝트 멤버 수정")
    void testModifyProjectMembers() {
        Long projectId = 1L;
        List<ProjectMemberRequestDto> projectMemberRequestDtoList = new ArrayList<>();
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "test1"));
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, "test2"));

        ProjectMember projectMember1 = new ProjectMember();
        ProjectMember projectMember2 = new ProjectMember();
        List<ProjectMember> projectMembers = Arrays.asList(projectMember1, projectMember2);

        given(projectMemberRepository.existsById(any()))
                .willReturn(true);
        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.of(new Project()));
        given(projectAuthorityRepository.findById(anyInt()))
                .willReturn(Optional.of(new ProjectAuthority()));
        given(projectMemberRepository.saveAllAndFlush(anyList()))
                .willReturn(projectMembers);

        projectMemberService.modifyProjectMembers(projectId, projectMemberRequestDtoList);
        verify(projectMemberRepository).saveAllAndFlush(anyList());
        ArgumentCaptor<List<ProjectMember>> projectMembersCaptor = ArgumentCaptor.forClass(List.class);
        verify(projectMemberRepository).saveAllAndFlush(projectMembersCaptor.capture());

        List<ProjectMember> savedProjectMembers = projectMembersCaptor.getValue();
        assertThat(savedProjectMembers).hasSize(2);
    }

    @Test
    @Order(5)
    @DisplayName("프로젝트 멤버 수정 실패 : 프로젝트 멤버가 등록된 적이 없는 경우")
    void testModifyProjectMembersFail() {
        Long projectId = 1L;
        String targetMemberId = "test";
        List<ProjectMemberRequestDto> projectMemberRequestDtoList = new ArrayList<>();
        projectMemberRequestDtoList.add(new ProjectMemberRequestDto(2, targetMemberId));

        given(projectMemberRepository.existsById(new ProjectMember.Pk(targetMemberId, projectId)))
                .willReturn(false);

        Assertions.assertThrows(NotFoundProjectMemberException.class, () -> {
            projectMemberService.modifyProjectMembers(projectId, projectMemberRequestDtoList);
        });
    }
}