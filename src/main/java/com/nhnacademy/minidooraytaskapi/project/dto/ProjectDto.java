package com.nhnacademy.minidooraytaskapi.project.dto;

import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;

public interface ProjectDto {
    Long getProjectId();
    ProjectStatus getProjectStatus();
    String getName();
    String getDescription();
}
