package com.nhnacademy.minidooraytaskapi.projectauthority.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Getter
@Setter
@NoArgsConstructor
public class ProjectAuthorityIdDto {
    @NotNull
    @Positive
    private int projectAuthorityId;
}
