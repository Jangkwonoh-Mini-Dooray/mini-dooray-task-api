package com.nhnacademy.minidooraytaskapi.projectstatus.service;

import com.nhnacademy.minidooraytaskapi.exception.DuplicateIntIdException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectStatusException;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusIdDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.projectstatus.repository.ProjectStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProjectStatusService implements ProjectStatusService {
    private final ProjectStatusRepository projectStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectStatusDto> getProjectStatuses() {
        return projectStatusRepository.getProjectStatuses();
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectStatusNameDto getProjectStatus(int projectStatusId) {
        return projectStatusRepository.getProjectStatus(projectStatusId);
    }

    @Override
    @Transactional
    public ProjectStatusIdDto createProjectStatus(ProjectStatusDto projectStatusDto) {
        if (projectStatusRepository.existsById(projectStatusDto.getProjectStatusId())) {
            throw new DuplicateIntIdException(projectStatusDto.getProjectStatusId());
        }
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setProjectStatusId(projectStatusDto.getProjectStatusId());
        projectStatus.setName(projectStatusDto.getName());
        ProjectStatus result = projectStatusRepository.saveAndFlush(projectStatus);
        ProjectStatusIdDto projectStatusIdDto = new ProjectStatusIdDto();
        projectStatusIdDto.setProjectStatusId(result.getProjectStatusId());
        return projectStatusIdDto;
    }

    @Override
    @Transactional
    public ProjectStatusIdDto updateMember(int projectStatusId, ProjectStatusNameDto projectStatusNameDto) {
        ProjectStatus projectStatus = projectStatusRepository.findById(projectStatusId)
                .orElseThrow(() -> new NotFoundProjectStatusException(projectStatusId));
        projectStatus.setName(projectStatusNameDto.getName());
        ProjectStatus result = projectStatusRepository.saveAndFlush(projectStatus);
        ProjectStatusIdDto projectStatusIdDto = new ProjectStatusIdDto();
        projectStatusIdDto.setProjectStatusId(result.getProjectStatusId());
        return projectStatusIdDto;
    }

    @Override
    @Transactional
    public void deleteProjectStatus(int projectStatusId) {
        if (!projectStatusRepository.existsById(projectStatusId)) {
            throw new NotFoundProjectStatusException(projectStatusId);
        }
        projectStatusRepository.deleteById(projectStatusId);
    }
}
