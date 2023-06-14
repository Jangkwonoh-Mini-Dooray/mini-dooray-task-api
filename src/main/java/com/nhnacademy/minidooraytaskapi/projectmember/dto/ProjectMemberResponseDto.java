package com.nhnacademy.minidooraytaskapi.projectmember.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberResponseDto {
     private int projectAuthorityId;
     private String targetMemberId;
}
