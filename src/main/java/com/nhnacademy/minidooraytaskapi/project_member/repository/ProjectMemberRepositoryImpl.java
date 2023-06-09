//package com.nhnacademy.minidooraytaskapi.project_member.repository;
//
//import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
//import com.nhnacademy.minidooraytaskapi.project_member.entity.ProjectMember;
//import com.nhnacademy.minidooraytaskapi.project_member.entity.QGetProject;
//import com.querydsl.core.types.Projections;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import java.util.List;
//
//public class ProjectMemberRepositoryImpl extends QuerydslRepositorySupport implements ProjectMemberRepositoryCustom {
//    public ProjectMemberRepositoryImpl() {
//        super(ProjectMember.class);
//    }
//
//    @Override
//    public List<ProjectMemberRequestDto> findTargetMembers(Long projectId) {
//        QGetProject getProject = QGetProject.getProject;
//
//        return from(getProject)
//                .where(getProject.pk.projectId.eq(projectId))
//                .select(Projections.bean(ProjectMemberRequestDto.class,
//                        getProject.pk.targetMemberId))
//                .fetch();
//    }
//}
