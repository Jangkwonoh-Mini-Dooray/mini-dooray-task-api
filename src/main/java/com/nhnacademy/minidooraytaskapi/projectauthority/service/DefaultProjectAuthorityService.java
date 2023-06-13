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
    @Transactional
    public ProjectAuthority createProjectAuthority(ProjectAuthorityDto projectAuthorityDto) {
        if (projectAuthorityRepository.existsById(projectAuthorityDto.getProjectAuthorityId())) {
            throw new DuplicateIntIdException(projectAuthorityDto.getProjectAuthorityId());
        }
        ProjectAuthority projectAuthority = new ProjectAuthority();
        projectAuthority.setProjectAuthorityId(projectAuthorityDto.getProjectAuthorityId());
        projectAuthority.setName(projectAuthorityDto.getName());
        return projectAuthorityRepository.saveAndFlush(projectAuthority);
    }

    @Override
    @Transactional
    public ProjectAuthority updateMember(int projectAuthorityId, ProjectAuthorityNameDto projectAuthorityNameDto) {
        ProjectAuthority projectAuthority = projectAuthorityRepository.findById(projectAuthorityId)
                .orElseThrow(() -> new NotFoundProjectAuthorityException(projectAuthorityId));
        projectAuthority.setName(projectAuthorityNameDto.getName());
        return projectAuthorityRepository.saveAndFlush(projectAuthority);
    }

    @Override
    @Transactional
    public void deleteProjectAuthority(int projectAuthorityId) {
        if (!projectAuthorityRepository.existsById(projectAuthorityId)) {
            throw new NotFoundProjectStatusException(projectAuthorityId);
        }
        projectAuthorityRepository.deleteById(projectAuthorityId);
    }
}
