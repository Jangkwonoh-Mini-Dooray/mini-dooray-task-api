package com.nhnacademy.minidooraytaskapi.projectauthority.repository;

import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.QProjectAuthority;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectAuthorityRepositoryImpl extends QuerydslRepositorySupport implements ProjectAuthorityRepositoryCustom {
    public ProjectAuthorityRepositoryImpl() {
        super(ProjectAuthority.class);
    }

    @Override
    public List<ProjectAuthorityDto> getProjectAuthorities() {
        QProjectAuthority projectAuthority = QProjectAuthority.projectAuthority;
        return from(projectAuthority)
                .select(Projections.bean(ProjectAuthorityDto.class,
                        projectAuthority.projectAuthorityId,
                        projectAuthority.name))
                .fetch();
    }

    @Override
    public ProjectAuthorityNameDto getProjectAuthority(int projectAuthorityId) {
        QProjectAuthority projectAuthority = QProjectAuthority.projectAuthority;
        return from(projectAuthority)
                .select(Projections.bean(ProjectAuthorityNameDto.class,
                        projectAuthority.name))
                .where(projectAuthority.projectAuthorityId.eq(projectAuthorityId))
                .fetchOne();
    }
}
