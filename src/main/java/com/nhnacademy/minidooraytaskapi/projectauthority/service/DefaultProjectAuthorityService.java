package com.nhnacademy.minidooraytaskapi.projectauthority.service;

import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityIdDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.repository.ProjectAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DefaultProjectAuthorityService implements ProjectAuthorityService {
    private final ProjectAuthorityRepository projectAuthorityRepository;
    @Override
    public List<ProjectAuthorityDto> getProjectAuthorities() {
        return projectAuthorityRepository.getProjectAuthorities();
    }

    @Override
    public ProjectAuthorityNameDto getProjectAuthority(int projectAuthorityId) {
        return projectAuthorityRepository.getProjectAuthority(projectAuthorityId);
    }

    @Override
    public ProjectAuthorityIdDto postProjectAuthority(ProjectAuthorityDto projectAuthorityDto) {
        return null;
    }

    @Override
    public ProjectAuthorityIdDto putProjectAuthority(ProjectAuthorityDto projectAuthorityDto, int projectAuthorityId) {
        return null;
    }

    @Override
    public void deleteProjectAuthority(int projectAuthorityId) {

    }
}
