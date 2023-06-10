package com.nhnacademy.minidooraytaskapi.milestone.service;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneIdDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneRequestDto;

import java.util.List;

public interface MilestoneService {
    List<MilestoneDto> getMilestones(Long projectId);
    MilestoneDto getMilestone(Long milestoneId);
    MilestoneIdDto createMilestone(Long projectId, MilestoneRequestDto milestoneRequestDto);
    MilestoneIdDto modifyMilestone(Long milestoneId, MilestoneRequestDto milestoneRequestDto);
    void deleteMilestone(Long milestoneId);
}
