package com.nhnacademy.minidooraytaskapi.projectstatus.service;

import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;

import java.util.List;

public interface ProjectStatusService {
    List<ProjectStatusDto> getProjectStatuses();
    ProjectStatusNameDto getProjectStatus(int projectStatusId);
}
