package com.nhnacademy.minidooraytaskapi.project_status.repository;


import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
}
