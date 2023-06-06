package com.nhnacademy.minidooraytaskapi.project.controller;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/{project-id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable("project-id") Long projectId) {
        ProjectDto project = projectService.getProject(projectId);
        return ResponseEntity.ok().body(project);
    }
}
