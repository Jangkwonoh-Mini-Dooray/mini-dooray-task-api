package com.nhnacademy.minidooraytaskapi.projectstatus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class ProjectStatusIdDto {
    @NotNull
    @Positive
    private int projectStatusId;
}
