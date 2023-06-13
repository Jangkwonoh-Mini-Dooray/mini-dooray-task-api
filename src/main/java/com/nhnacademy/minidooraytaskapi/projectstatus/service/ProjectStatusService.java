package com.nhnacademy.minidooraytaskapi.projectstatus.service;

import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusIdDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;

import java.util.List;

public interface ProjectStatusService {
    List<ProjectStatusDto> getProjectStatuses();
    ProjectStatusNameDto getProjectStatus(int projectStatusId);
    ProjectStatusIdDto createProjectStatus(ProjectStatusDto projectStatusDto);
    ProjectStatusIdDto updateMember(int projectStatusId, ProjectStatusNameDto projectStatusNameDto);
    void deleteProjectStatus(int projectStatusId);
}
