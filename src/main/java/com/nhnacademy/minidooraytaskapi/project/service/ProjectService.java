package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;

public interface ProjectService {
    ProjectDto getProject(Long projectId);
}
