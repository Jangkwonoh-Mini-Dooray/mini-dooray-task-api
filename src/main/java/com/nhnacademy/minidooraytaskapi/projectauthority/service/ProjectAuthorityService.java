package com.nhnacademy.minidooraytaskapi.projectauthority.service;

import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;

import java.util.List;

public interface ProjectAuthorityService {
    List<ProjectAuthorityDto> getProjectAuthorities();
    ProjectAuthorityNameDto getProjectAuthority(int projectAuthorityId);
    ProjectAuthority createProjectAuthority(ProjectAuthorityDto projectAuthorityDto);
    ProjectAuthority updateMember(int projectAuthorityId, ProjectAuthorityNameDto projectAuthorityNameDto);
    void deleteProjectAuthority(int projectAuthorityId);
}
