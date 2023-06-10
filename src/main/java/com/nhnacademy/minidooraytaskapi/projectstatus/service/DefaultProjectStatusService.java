package com.nhnacademy.minidooraytaskapi.projectstatus.service;

import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
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
}
