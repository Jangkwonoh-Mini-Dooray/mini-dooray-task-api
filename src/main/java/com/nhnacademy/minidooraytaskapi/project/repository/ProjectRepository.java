package com.nhnacademy.minidooraytaskapi.project.repository;


import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<ProjectDto> findAllProjectDto(String memberId);
}
