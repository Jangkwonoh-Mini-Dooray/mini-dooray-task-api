package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectStatusException;
import com.nhnacademy.minidooraytaskapi.exception.NotfoundProjectException;
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
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultProjectService implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public ProjectDto getProject(Long projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    @Override
    public ProjectIdDto createProject(ProjectRequestDto projectRequestDto) {
        Project project = new Project();
        int projectStatusId = ProjectStatusCode.getValue(projectRequestDto.getProjectStatusName());
        ProjectStatus projectStatus = projectStatusRepository
                .findById(projectStatusId)
                .orElseThrow(NotFoundProjectStatusException::new);
        project.setProjectStatus(projectStatus);
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        Project result = projectRepository.saveAndFlush(project);
        return new ProjectIdDto(result.getProjectId());
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.findById(projectId)
                .orElseThrow(() -> new NotfoundProjectException(projectId));
        projectRepository.deleteById(projectId);
    }
}
