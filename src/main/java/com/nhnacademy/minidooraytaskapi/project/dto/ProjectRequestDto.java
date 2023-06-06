package com.nhnacademy.minidooraytaskapi.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {
    private String projectStatusName;
    private String name;
    private String description;
}
