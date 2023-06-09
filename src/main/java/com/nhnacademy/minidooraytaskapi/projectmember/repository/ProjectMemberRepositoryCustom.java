package com.nhnacademy.minidooraytaskapi.projectmember.repository;

import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberRequestDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectMemberRepositoryCustom {
    List<ProjectMemberRequestDto> findProjectMembers(Long projectId);
}
