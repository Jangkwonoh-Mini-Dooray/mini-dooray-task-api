package com.nhnacademy.minidooraytaskapi.get_project.repository;

import com.nhnacademy.minidooraytaskapi.get_project.dto.TargetMemberIdDto;
import com.nhnacademy.minidooraytaskapi.get_project.entity.GetProject;
import com.nhnacademy.minidooraytaskapi.get_project.entity.QGetProject;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class GetProjectRepositoryImpl extends QuerydslRepositorySupport implements GetProjectRepositoryCustom {
    public GetProjectRepositoryImpl() {
        super(GetProject.class);
    }

    @Override
    public List<TargetMemberIdDto> findTargetMembers(Long projectId) {
        QGetProject getProject = QGetProject.getProject;

        return from(getProject)
                .where(getProject.pk.projectId.eq(projectId))
                .select(Projections.bean(TargetMemberIdDto.class,
                        getProject.pk.targetMemberId))
                .fetch();
    }
}
