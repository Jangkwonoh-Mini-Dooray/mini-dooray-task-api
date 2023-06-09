package com.nhnacademy.minidooraytaskapi.project_member.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectAuthorityException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.entity.ProjectMember;
import com.nhnacademy.minidooraytaskapi.project_member.repository.ProjectMemberRepository;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.project_authority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.project_authority.repository.ProjectAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGetProjectService implements GetProjectService {
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final ProjectAuthorityRepository projectAuthorityRepository;

    @Override
    public List<ProjectMemberRequestDto> getTargetMembers(Long projectId) {
        return projectMemberRepository.findTargetMembers(projectId);
    }

    @Override
    public void addTargetMembers(Long projectId, List<ProjectMemberRequestDto> targetMembers) {
        projectMemberRepository.saveAllAndFlush(convertToGetProject(projectId, targetMembers));
    }


    // helper
    public List<ProjectMember> convertToGetProject(Long projectId, List<ProjectMemberRequestDto> targetMembers) {
        List<ProjectMember> projectMembers = new ArrayList<>();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(projectId));

        for (ProjectMemberRequestDto dto : targetMembers) {
            String targetMemberId = dto.getTargetMemberId();
            int projectAuthorityId = dto.getProjectAuthorityId();

            ProjectAuthority projectAuthority = projectAuthorityRepository.findById(projectAuthorityId)
                            .orElseThrow(() -> new NotFoundProjectAuthorityException(projectAuthorityId));
            projectMembers.add(new ProjectMember(new ProjectMember.Pk(targetMemberId, projectId), project, projectAuthority));
        }
        return projectMembers;
    }
}
