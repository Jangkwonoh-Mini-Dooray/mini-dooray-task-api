package com.nhnacademy.minidooraytaskapi.projectstatus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStatusNameDto {
    @NotBlank
    private String name;
}
