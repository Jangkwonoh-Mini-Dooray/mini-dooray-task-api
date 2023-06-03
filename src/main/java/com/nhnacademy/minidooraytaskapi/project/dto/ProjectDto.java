package com.nhnacademy.minidooraytaskapi.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProjectDto {
    private Long projectId;
    private Long projectStatusId;
    private String name;
    private String description;
}
