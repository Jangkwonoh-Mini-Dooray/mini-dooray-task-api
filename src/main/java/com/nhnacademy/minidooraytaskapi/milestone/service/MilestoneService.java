package com.nhnacademy.minidooraytaskapi.milestone.service;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;

import java.util.List;

public interface MilestoneService {
    List<MilestoneDto> getMilestones(Long projectId);
}
