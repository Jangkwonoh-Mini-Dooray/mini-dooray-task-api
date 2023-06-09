package com.nhnacademy.minidooraytaskapi.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectDto {
    private Long projectId;
    private String projectStatusName;
    private String name;
    private String description;
}