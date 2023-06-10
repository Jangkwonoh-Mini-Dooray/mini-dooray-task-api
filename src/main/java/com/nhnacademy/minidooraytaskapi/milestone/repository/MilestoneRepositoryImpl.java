package com.nhnacademy.minidooraytaskapi.milestone.repository;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.milestone.entity.QMilestone;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MilestoneRepositoryImpl extends QuerydslRepositorySupport implements MilestoneRepositoryCustom {
    public MilestoneRepositoryImpl() {
        super(Milestone.class);
    }

    @Override
    public List<MilestoneDto> findMilestones(Long projectId) {
        QMilestone milestone = QMilestone.milestone;

        return from(milestone)
                .where(milestone.project.projectId.eq(projectId))
                .select(Projections.constructor(MilestoneDto.class,
                        milestone.milestoneId,
                        milestone.name,
                        milestone.startPeriod,
                        milestone.endPeriod,
                        milestone.status))
                .fetch();
    }

    @Override
    public MilestoneDto findMilestone(Long milestoneId) {
        QMilestone milestone = QMilestone.milestone;

        return from(milestone)
                .where(milestone.milestoneId.eq(milestoneId))
                .select(Projections.bean(MilestoneDto.class,
                        milestone.milestoneId,
                        milestone.name,
                        milestone.startPeriod,
                        milestone.endPeriod,
                        milestone.status))
                .fetchOne();
    }
}
