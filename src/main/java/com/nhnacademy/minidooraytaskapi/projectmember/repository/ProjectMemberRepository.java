package com.nhnacademy.minidooraytaskapi.projectmember.repository;

import com.nhnacademy.minidooraytaskapi.projectmember.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk>, ProjectMemberRepositoryCustom {
}
