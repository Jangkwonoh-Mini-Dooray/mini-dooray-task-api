package com.nhnacademy.minidooraytaskapi.project.controller;

import com.nhnacademy.minidooraytaskapi.exception.CreateProjectException;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.service.ProjectService;
import com.nhnacademy.minidooraytaskapi.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<Response> createProject(@RequestBody @Valid ProjectRequestDto projectRequestDto,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CreateProjectException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        projectService.createProject(projectRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{project-id}")
    public ResponseEntity<Response> deleteProject(@PathVariable("project-id") Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
