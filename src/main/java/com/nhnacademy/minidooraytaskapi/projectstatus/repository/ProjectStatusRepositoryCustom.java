package com.nhnacademy.minidooraytaskapi.projectstatus.repository;

import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectStatusRepositoryCustom {
    List<ProjectStatusDto> getProjectStatuses();
    ProjectStatusNameDto  getProjectStatus(int projectStatusId);
}
