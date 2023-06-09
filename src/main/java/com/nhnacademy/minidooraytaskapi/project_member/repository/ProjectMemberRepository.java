package com.nhnacademy.minidooraytaskapi.project_member.repository;

import com.nhnacademy.minidooraytaskapi.project_member.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk>, ProjectMemberRepositoryCustom {
}
