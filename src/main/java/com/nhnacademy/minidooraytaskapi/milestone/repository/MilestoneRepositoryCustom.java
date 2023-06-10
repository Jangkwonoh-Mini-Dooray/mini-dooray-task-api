package com.nhnacademy.minidooraytaskapi.milestone.repository;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MilestoneRepositoryCustom {
    List<MilestoneDto> findMilestones(Long projectId);
    MilestoneDto findMilestone(Long milestoneId);
}
