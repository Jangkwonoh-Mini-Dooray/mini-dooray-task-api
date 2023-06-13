package com.nhnacademy.minidooraytaskapi.projectauthority.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class ProjectAuthorityDto {
    @NotNull
    @Positive
    private int projectAuthorityId;

    @NotBlank
    private String name;
}
