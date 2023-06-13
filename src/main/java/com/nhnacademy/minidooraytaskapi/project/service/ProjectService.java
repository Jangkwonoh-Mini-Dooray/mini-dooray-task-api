package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getProjects();
    List<ProjectDto> getProjectsByMemberId(String memberId);
    ProjectDto getProject(Long projectId);

    ProjectIdDto createProject(ProjectRequestDto projectRequestDto);

    ProjectIdDto modifyProject(ProjectRequestDto projectRequestDto, Long projectId);

    boolean deleteProject(Long projectId);
}
