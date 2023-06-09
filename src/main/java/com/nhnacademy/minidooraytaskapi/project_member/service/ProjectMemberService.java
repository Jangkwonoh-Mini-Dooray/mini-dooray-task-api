package com.nhnacademy.minidooraytaskapi.project_member.service;

import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberDeleteRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMemberRequestDto> getProjectMembers(Long projectId);
    void addProjectMembers(Long projectId, List<ProjectMemberRequestDto> targetMembers);
    void modifyProjectMembers(Long projectId, List<ProjectMemberRequestDto> targetMembers);
    void deleteProjectMembers(Long projectId, List<ProjectMemberDeleteRequestDto> projectMemberDeleteRequestDtoList);
}
