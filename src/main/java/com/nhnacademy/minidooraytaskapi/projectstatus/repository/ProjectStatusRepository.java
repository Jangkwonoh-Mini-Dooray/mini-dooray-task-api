package com.nhnacademy.minidooraytaskapi.projectstatus.repository;


import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer>, ProjectStatusRepositoryCustom {
}
