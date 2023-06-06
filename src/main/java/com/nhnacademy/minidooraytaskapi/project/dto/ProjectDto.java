package com.nhnacademy.minidooraytaskapi.project.dto;

import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class ProjectDto {
    private Long projectId;
    private ProjectStatus projectStatus;
    private String name;
    private String description;
}
