package com.nhnacademy.minidooraytaskapi.milestone.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundMilestoneException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneIdDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneRequestDto;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.milestone.repository.MilestoneRepository;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultMilestoneService implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MilestoneDto> getMilestones(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new NotFoundProjectException(projectId);
        }
        return milestoneRepository.findMilestones(projectId);
    }

    @Override
    public MilestoneDto getMilestone(Long milestoneId) {
        if (!milestoneRepository.existsById(milestoneId)) {
            throw new NotFoundMilestoneException(milestoneId);
        }
        return milestoneRepository.findMilestone(milestoneId);
    }

    @Override
    public MilestoneIdDto createMilestone(Long projectId, MilestoneRequestDto dto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(projectId));
        Milestone milestone = new Milestone(dto.getName(), dto.getStartPeriod(), dto.getEndPeriod(), dto.getStatus(), project);
        milestoneRepository.saveAndFlush(milestone);
        return new MilestoneIdDto(milestone.getMilestoneId());
    }

    @Override
    public MilestoneIdDto modifyMilestone(Long milestoneId, MilestoneRequestDto dto) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new NotFoundMilestoneException(milestoneId));
        milestone.update(dto.getName(), dto.getStartPeriod(), dto.getEndPeriod(), dto.getStatus());
        milestoneRepository.saveAndFlush(milestone);
        return new MilestoneIdDto(milestone.getMilestoneId());
    }

    @Override
    public void deleteMilestone(Long milestoneId) {
        if (!milestoneRepository.existsById(milestoneId)) {
            throw new NotFoundMilestoneException(milestoneId);
        }
        milestoneRepository.deleteById(milestoneId);
    }
}
