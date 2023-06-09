package com.nhnacademy.minidooraytaskapi.projectmember.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberDeleteRequestDto {
    private String targetMemberId;
}
