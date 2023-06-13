package com.nhnacademy.minidooraytaskapi.projectauthority.service;

import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityIdDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;

import java.util.List;

public interface ProjectAuthorityService {
    List<ProjectAuthorityDto> getProjectAuthorities();
    ProjectAuthorityNameDto getProjectAuthority(int projectAuthorityId);

    ProjectAuthorityIdDto postProjectAuthority(ProjectAuthorityDto projectAuthorityDto);

    ProjectAuthorityIdDto putProjectAuthority(ProjectAuthorityDto projectAuthorityDto, int projectAuthorityId);

    void deleteProjectAuthority(int projectAuthorityId);
}
