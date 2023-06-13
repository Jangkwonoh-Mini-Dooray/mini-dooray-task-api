package com.nhnacademy.minidooraytaskapi.projectauthority.repository;

import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.QProjectAuthority;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectAuthorityRepositoryImpl extends QuerydslRepositorySupport implements ProjectAuthorityRepositoryCustom {

    public ProjectAuthorityRepositoryImpl() {
        super(ProjectAuthority.class);
    }

    @Override
    public List<ProjectAuthorityDto> getProjectAuthorities() {
        QProjectAuthority qProjectAuthority = QProjectAuthority.projectAuthority;
        return from(qProjectAuthority)
                .select(Projections.bean(ProjectAuthorityDto.class,
                        qProjectAuthority.projectAuthorityId,
                        qProjectAuthority.name))
                .fetch();
    }

    @Override
    public ProjectAuthorityNameDto getProjectAuthority(int projectAuthorityId) {
        QProjectAuthority qProjectAuthority = QProjectAuthority.projectAuthority;
        return from(qProjectAuthority)
                .where(qProjectAuthority.projectAuthorityId.eq(projectAuthorityId))
                .select(Projections.bean(ProjectAuthorityNameDto.class,
                        qProjectAuthority.name))
                .fetchOne();
    }
}
