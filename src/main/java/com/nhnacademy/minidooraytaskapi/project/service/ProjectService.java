package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getProjects(String memberId);
    ProjectDto getProject(Long projectId);
    ProjectIdDto createProject(ProjectRequestDto projectRequestDto);
    ProjectIdDto modifyProject(ProjectRequestDto projectRequestDto, Long projectId);
    boolean deleteProject(Long projectId);
}
