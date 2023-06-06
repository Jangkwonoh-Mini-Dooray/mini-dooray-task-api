package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectStatusException;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.project_status.code.ProjectStatusCode;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.project_status.repository.ProjectStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultProjectService implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;

    @Override
    public List<ProjectDto> getProjects(String memberId) {
        return projectRepository.findByMemberId(memberId);
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
                .orElseThrow(NotFoundProjectStatusException::new);
        Project project = new Project(projectStatus, projectRequestDto.getName(), projectRequestDto.getDescription());
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
                .orElseThrow(NotFoundProjectStatusException::new);
        project.setProjectStatus(projectStatus);
        project.setName(project.getName());
        project.setDescription(project.getDescription());
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
