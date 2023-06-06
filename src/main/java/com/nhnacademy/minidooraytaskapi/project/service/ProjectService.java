package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;

public interface ProjectService {
    ProjectDto getProject(Long projectId);
    ProjectIdDto createProject(ProjectRequestDto projectRequestDto);
    void deleteProject(Long projectId);
}
