package com.nhnacademy.minidooraytaskapi.project_member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberRequestDto {
     private int projectAuthorityId;
     private String targetMemberId;
}
