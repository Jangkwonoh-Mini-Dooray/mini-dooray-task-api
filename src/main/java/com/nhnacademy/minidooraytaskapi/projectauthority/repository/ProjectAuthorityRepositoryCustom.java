package com.nhnacademy.minidooraytaskapi.projectauthority.repository;

import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectAuthorityRepositoryCustom {
    List<ProjectAuthorityDto> getProjectAuthorities();
    ProjectAuthorityNameDto getProjectAuthority(int projectAuthorityId);
}
