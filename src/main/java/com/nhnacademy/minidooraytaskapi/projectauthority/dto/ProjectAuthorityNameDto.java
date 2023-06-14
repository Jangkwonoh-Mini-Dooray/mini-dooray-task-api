package com.nhnacademy.minidooraytaskapi.projectauthority.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ProjectAuthorityNameDto {
    @NotBlank
    private String name;
}
