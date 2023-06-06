package com.nhnacademy.minidooraytaskapi.project.controller;

import com.nhnacademy.minidooraytaskapi.exception.ProjectBindingResultException;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectIdDto;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.service.ProjectService;
import com.nhnacademy.minidooraytaskapi.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/list/{member-id}")
    public ResponseEntity<List<ProjectDto>> getProjects(@PathVariable("member-id") String memberId) {
        List<ProjectDto> projectList = projectService.getProjects(memberId);
        return ResponseEntity.ok().body(projectList);
    }

    @GetMapping("/{project-id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable("project-id") Long projectId) {
        ProjectDto project = projectService.getProject(projectId);
        return ResponseEntity.ok().body(project);
    }

    @PostMapping
    public ResponseEntity<ProjectIdDto> createProject(@RequestBody @Valid ProjectRequestDto projectRequestDto,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProjectBindingResultException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        ProjectIdDto result = projectService.createProject(projectRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{project-id}")
    public ResponseEntity<ProjectIdDto> modifyProject(@RequestBody @Valid ProjectRequestDto projectRequestDto,
                                                      @PathVariable("project-id") Long projectId,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProjectBindingResultException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        ProjectIdDto result = projectService.modifyProject(projectRequestDto, projectId);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/{project-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteProject(@PathVariable("project-id") Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().body(new Response("ok"));
    }
}
