package com.nhnacademy.minidooraytaskapi.project_member.repository;

import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectMemberRepositoryCustom {
    List<ProjectMemberRequestDto> findTargetMembers(Long projectId);
}
