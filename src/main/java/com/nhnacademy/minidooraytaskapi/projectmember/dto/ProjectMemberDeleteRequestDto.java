package com.nhnacademy.minidooraytaskapi.projectmember.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberDeleteRequestDto {
    @NotBlank(message = "id 없음")
    private String targetMemberId;
}
