package com.nhnacademy.minidooraytaskapi.projectauthority.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectAuthorityException;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityIdDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.projectauthority.repository.ProjectAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class DefaultProjectAuthorityService implements ProjectAuthorityService {
    private final ProjectAuthorityRepository projectAuthorityRepository;
    @Override
    @Transactional(readOnly = true)
    public List<ProjectAuthorityDto> getProjectAuthorities() {
        return projectAuthorityRepository.getProjectAuthorities();
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectAuthorityNameDto getProjectAuthority(int projectAuthorityId) {
        return projectAuthorityRepository.getProjectAuthority(projectAuthorityId);
    }

    @Override
    public ProjectAuthorityIdDto postProjectAuthority(ProjectAuthorityDto projectAuthorityDto) {
        ProjectAuthority projectAuthority = new ProjectAuthority(projectAuthorityDto.getProjectAuthorityId(), projectAuthorityDto.getName());
        ProjectAuthority result = projectAuthorityRepository.saveAndFlush(projectAuthority);
        return new ProjectAuthorityIdDto(result.getProjectAuthorityId());
    }
    @Override
    public ProjectAuthorityIdDto putProjectAuthority(ProjectAuthorityDto projectAuthorityDto, int projectAuthorityId) {
        ProjectAuthority projectAuthority = projectAuthorityRepository.findById(projectAuthorityId)
                .orElseThrow(() -> new NotFoundProjectAuthorityException(projectAuthorityId));
        projectAuthority.setName(projectAuthorityDto.getName());
        ProjectAuthority result = projectAuthorityRepository.saveAndFlush(projectAuthority);
        return new ProjectAuthorityIdDto(result.getProjectAuthorityId());
    }
    @Override
    public void deleteProjectAuthority(int projectAuthorityId) {
        if (!projectAuthorityRepository.existsById(projectAuthorityId)) {
            throw new NotFoundProjectAuthorityException(projectAuthorityId);
        }
        projectAuthorityRepository.deleteById(projectAuthorityId);
    }
}