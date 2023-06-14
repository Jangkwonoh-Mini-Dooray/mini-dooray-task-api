package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectStatusException;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.projectstatus.code.ProjectStatusCode;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.projectstatus.repository.ProjectStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultProjectService implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<ProjectDto> getProjectsByMemberId(String memberId) {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        List<ProjectIdDto> projectIdDtoList = projectRepository.findAllByMemberId(memberId);
        for (ProjectIdDto dto : projectIdDtoList) {
            projectDtoList.add(projectRepository.findByProjectId(dto.getProjectId()));
        }
        return projectDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDto getProject(Long projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    @Override
    public ProjectIdDto createProject(ProjectRequestDto projectRequestDto) {
        int projectStatusId = ProjectStatusCode.getValue(projectRequestDto.getProjectStatusName());
        ProjectStatus projectStatus = projectStatusRepository
                .findById(projectStatusId)
                .orElseThrow(() -> new NotFoundProjectStatusException(projectStatusId));
        Project project = new Project(projectStatus, projectRequestDto);
        Project result = projectRepository.saveAndFlush(project);
        return new ProjectIdDto(result.getProjectId());
    }

    @Override
    public ProjectIdDto modifyProject(ProjectRequestDto projectRequestDto, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(projectId));
        int projectStatusId = ProjectStatusCode.getValue(projectRequestDto.getProjectStatusName());
        ProjectStatus projectStatus = projectStatusRepository
                .findById(projectStatusId)
                .orElseThrow(() -> new NotFoundProjectStatusException(projectStatusId));
        project.modify(projectStatus, projectRequestDto);
        Project result = projectRepository.saveAndFlush(project);
        return new ProjectIdDto(result.getProjectId());
    }

    @Override
    public boolean deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(projectId));
        projectRepository.deleteById(project.getProjectId());
        return true;
    }
}
