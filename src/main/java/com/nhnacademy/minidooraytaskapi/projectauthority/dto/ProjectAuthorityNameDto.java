package com.nhnacademy.minidooraytaskapi.projectauthority.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAuthorityNameDto {
    @NotBlank
    private String name;
}
