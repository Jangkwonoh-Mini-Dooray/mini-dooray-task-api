package com.nhnacademy.minidooraytaskapi.projectstatus.repository;

import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.QProjectStatus;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectStatusRepositoryImpl extends QuerydslRepositorySupport implements ProjectStatusRepositoryCustom {

    public ProjectStatusRepositoryImpl() {
        super(ProjectStatus.class);
    }

    @Override
    public List<ProjectStatusDto> getProjectStatuses() {
        QProjectStatus qProjectStatus = QProjectStatus.projectStatus;
        return from(qProjectStatus)
                .select(Projections.bean(ProjectStatusDto.class,
                        qProjectStatus.projectStatusId,
                        qProjectStatus.name))
                .fetch();
    }

    @Override
    public ProjectStatusNameDto getProjectStatus(int projectStatusId) {
        QProjectStatus qProjectStatus = QProjectStatus.projectStatus;
        return from(qProjectStatus)
                .where(qProjectStatus.projectStatusId.eq(projectStatusId))
                .select(Projections.bean(ProjectStatusNameDto.class,
                        qProjectStatus.name))
                .fetchOne();
    }
}
