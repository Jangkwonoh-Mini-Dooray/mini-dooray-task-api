package com.nhnacademy.minidooraytaskapi.projectmember.repository;

import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberResponseDto;
import com.nhnacademy.minidooraytaskapi.projectmember.entity.ProjectMember;
import com.nhnacademy.minidooraytaskapi.projectmember.entity.QProjectMember;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectMemberRepositoryImpl extends QuerydslRepositorySupport implements ProjectMemberRepositoryCustom {
    public ProjectMemberRepositoryImpl() {
        super(ProjectMember.class);
    }

    @Override
    public List<ProjectMemberResponseDto> findProjectMembers(Long projectId) {
        QProjectMember projectMember = QProjectMember.projectMember;

        return from(projectMember)
                .where(projectMember.project.projectId.eq(projectId))
                .select(Projections.constructor(ProjectMemberResponseDto.class,
                        projectMember.projectAuthority.projectAuthorityId,
                        projectMember.pk.targetMemberId))
                .fetch();
    }
}
