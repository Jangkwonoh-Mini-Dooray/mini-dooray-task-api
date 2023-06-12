package com.nhnacademy.minidooraytaskapi.project.repository;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectRepositoryCustom {
    List<ProjectIdDto> findAllByMemberId(String memberId);
    ProjectDto findByProjectId(Long projectId);
}
