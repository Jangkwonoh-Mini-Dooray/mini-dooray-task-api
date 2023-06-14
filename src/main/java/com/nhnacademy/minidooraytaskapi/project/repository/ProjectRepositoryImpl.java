package com.nhnacademy.minidooraytaskapi.project.repository;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.entity.QProject;
import com.nhnacademy.minidooraytaskapi.projectmember.entity.QProjectMember;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.QProjectStatus;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectRepositoryImpl extends QuerydslRepositorySupport implements ProjectRepositoryCustom {
    public ProjectRepositoryImpl() {
        super(Project.class);
    }

    @Override
    public List<ProjectIdDto> findAllByMemberId(String memberId) {
        QProject project = QProject.project;
        QProjectMember projectMember = QProjectMember.projectMember;

        return from(project)
                .innerJoin(projectMember)
                .on(project.eq(projectMember.project))
                .where(projectMember.pk.targetMemberId.eq(memberId))
                .select(Projections.constructor(ProjectIdDto.class,
                        project.projectId))
                .fetch();
    }

    @Override
    public ProjectDto findByProjectId(Long projectId) {
        QProject project = QProject.project;
        QProjectStatus projectStatus = QProjectStatus.projectStatus;

        return from(project)
                .innerJoin(project.projectStatus, projectStatus)
                .where(project.projectId.eq(projectId))
                .select(Projections.constructor(ProjectDto.class,
                        project.projectId,
                        project.projectStatus.name,
                        project.name,
                        project.description))
                .fetchOne();
    }
}
