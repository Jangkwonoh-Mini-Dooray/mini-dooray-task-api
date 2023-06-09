package com.nhnacademy.minidooraytaskapi.project_member.service;

import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.repository.ProjectMemberRepository;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("ProjectMember : Service 테스트")
class DefaultDefaultProjectMemberServiceTest {
    @Autowired
    ProjectMemberService projectMemberService;
    @MockBean
    ProjectMemberRepository projectMemberRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("Project ID 로 프로젝트 멤버 조회 서비스")
    void testGetTargetMembers() {
        List<ProjectMemberRequestDto> targetMembers = new ArrayList<>();
        targetMembers.add(new ProjectMemberRequestDto(2, "test1"));
        targetMembers.add(new ProjectMemberRequestDto(2, "test2"));

        given(projectMemberRepository.findTargetMembers(anyLong()))
                .willReturn(targetMembers);

        List<ProjectMemberRequestDto> actual = projectMemberService.getTargetMembers(1L);

        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getTargetMemberId()).isEqualTo("test1");
        assertThat(actual.get(1).getTargetMemberId()).isEqualTo("test2");
    }
}