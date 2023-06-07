package com.nhnacademy.minidooraytaskapi.get_project.repository;

import com.nhnacademy.minidooraytaskapi.get_project.entity.GetProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GetProjectRepository extends JpaRepository<GetProject, GetProject.Pk>, GetProjectRepositoryCustom {
}
