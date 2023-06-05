package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto getProject(Long projectId);
}
