package com.nhnacademy.minidooraytaskapi.get_project.service;

import com.nhnacademy.minidooraytaskapi.get_project.dto.TargetMemberIdDto;
import com.nhnacademy.minidooraytaskapi.get_project.repository.GetProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGetProjectService implements GetProjectService {
    private final GetProjectRepository getProjectRepository;

    @Override
    public List<TargetMemberIdDto> getTargetMembers(Long projectId) {
        return getProjectRepository.findTargetMembers(projectId);
    }
}
