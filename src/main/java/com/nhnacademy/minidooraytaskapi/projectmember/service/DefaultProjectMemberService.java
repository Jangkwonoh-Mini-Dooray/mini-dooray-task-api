package com.nhnacademy.minidooraytaskapi.projectmember.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectAuthorityException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectMemberException;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberDeleteRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.entity.ProjectMember;
import com.nhnacademy.minidooraytaskapi.projectmember.repository.ProjectMemberRepository;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.projectauthority.repository.ProjectAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultProjectMemberService implements ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final ProjectAuthorityRepository projectAuthorityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectMemberRequestDto> getProjectMembers(Long projectId) {
        return projectMemberRepository.findProjectMembers(projectId);
    }

    @Override
    public void addProjectMembers(Long projectId, List<ProjectMemberRequestDto> projectMemberRequestDtoList) {
        List<ProjectMember> projectMemberList = convertToProjectMember(projectId, projectMemberRequestDtoList);
        projectMemberRepository.saveAllAndFlush(projectMemberList);
    }

    @Override
    public void modifyProjectMembers(Long projectId, List<ProjectMemberRequestDto> projectMemberRequestDtoList) {
        for (ProjectMemberRequestDto dto : projectMemberRequestDtoList) {
            if (!projectMemberRepository.existsById(new ProjectMember.Pk(dto.getTargetMemberId(), projectId))) {
                throw new NotFoundProjectMemberException();
            }
        }
        List<ProjectMember> projectMemberList = convertToProjectMember(projectId, projectMemberRequestDtoList);
        projectMemberRepository.saveAllAndFlush(projectMemberList);
    }

    @Override
    public void deleteProjectMembers(Long projectId, List<ProjectMemberDeleteRequestDto> projectMemberDeleteRequestDtoList) {
        List<ProjectMemberDeleteRequestDto> existList = projectMemberDeleteRequestDtoList
                .stream().filter(dto -> projectMemberRepository.existsById(new ProjectMember.Pk(dto.getTargetMemberId(), projectId)))
                .collect(Collectors.toUnmodifiableList());
        existList
                .forEach(dto -> projectMemberRepository.deleteById(new ProjectMember.Pk(dto.getTargetMemberId(), projectId)));
    }

    // helper
    public List<ProjectMember> convertToProjectMember(Long projectId, List<ProjectMemberRequestDto> targetMembers) {
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
