package com.nhnacademy.minidooraytaskapi.projectmember.service;

import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberDeleteRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberResponseDto;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMemberResponseDto> getProjectMembers(Long projectId);
    void addProjectMembers(Long projectId, List<ProjectMemberResponseDto> targetMembers);
    void modifyProjectMembers(Long projectId, List<ProjectMemberResponseDto> targetMembers);
    void deleteProjectMembers(Long projectId, List<ProjectMemberDeleteRequestDto> projectMemberDeleteRequestDtoList);
}
