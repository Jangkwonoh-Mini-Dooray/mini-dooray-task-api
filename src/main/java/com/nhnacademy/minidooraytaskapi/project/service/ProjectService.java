package com.nhnacademy.minidooraytaskapi.project.service;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getProjects(String memberId);
}
