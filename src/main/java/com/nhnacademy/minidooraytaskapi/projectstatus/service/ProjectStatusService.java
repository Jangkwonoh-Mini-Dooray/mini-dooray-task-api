package com.nhnacademy.minidooraytaskapi.projectstatus.service;

import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;

import java.util.List;

public interface ProjectStatusService {
    List<ProjectStatusDto> getProjectStatuses();
    ProjectStatusNameDto getProjectStatus(int projectStatusId);
    ProjectStatus createProjectStatus(ProjectStatusDto projectStatusDto);
    ProjectStatus updateMember(int projectStatusId, ProjectStatusNameDto projectStatusNameDto);
    void deleteProjectStatus(int projectStatusId);
}
