package com.nhnacademy.minidooraytaskapi.project_member.service;

import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMemberRequestDto> getTargetMembers(Long projectId);
    void addTargetMembers(Long projectId, List<ProjectMemberRequestDto> targetMembers);
}
