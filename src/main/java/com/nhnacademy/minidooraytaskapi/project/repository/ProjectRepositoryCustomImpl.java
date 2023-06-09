package com.nhnacademy.minidooraytaskapi.project.repository;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.entity.QProject;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.QProjectStatus;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProjectRepositoryCustomImpl extends QuerydslRepositorySupport implements ProjectRepositoryCustom {
    public ProjectRepositoryCustomImpl() {
        super(Project.class);
    }

    @Override
    public ProjectDto findByProjectId(Long projectId) {
        QProject project = QProject.project;
        QProjectStatus projectStatus = QProjectStatus.projectStatus;

        return from(project)
                .innerJoin(project.projectStatus, projectStatus)
                .where(project.projectId.eq(projectId))
                .select(Projections.bean(ProjectDto.class,
                        project.projectId,
                        project.projectStatus.name,
                        project.name,
                        project.description))
                .fetchOne();
    }
}
