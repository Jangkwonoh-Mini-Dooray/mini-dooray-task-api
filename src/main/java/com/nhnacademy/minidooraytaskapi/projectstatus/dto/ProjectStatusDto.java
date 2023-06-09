package com.nhnacademy.minidooraytaskapi.projectstatus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStatusDto {
    @NotNull
    @Positive
    private int projectStatusId;

    @NotBlank
    private String name;
}
