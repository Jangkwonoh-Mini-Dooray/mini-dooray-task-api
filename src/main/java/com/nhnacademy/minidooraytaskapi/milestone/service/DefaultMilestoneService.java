package com.nhnacademy.minidooraytaskapi.milestone.service;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultMilestoneService implements MilestoneService {
    private final MilestoneRepository milestoneRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MilestoneDto> getMilestones(Long projectId) {
        return milestoneRepository.findMilestones(projectId);
    }
}
