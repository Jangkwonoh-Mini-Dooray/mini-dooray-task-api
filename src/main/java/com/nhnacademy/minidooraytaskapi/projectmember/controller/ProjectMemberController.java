package com.nhnacademy.minidooraytaskapi.projectmember.controller;

import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberDeleteRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.service.ProjectMemberService;
import com.nhnacademy.minidooraytaskapi.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects/{project-id}/members")
@RequiredArgsConstructor
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<ProjectMemberRequestDto>> getProjectMembers(@PathVariable("project-id") Long projectId) {
        List<ProjectMemberRequestDto> targetMembers = projectMemberService.getProjectMembers(projectId);
        return ResponseEntity.ok().body(targetMembers);
    }

    @PostMapping
    public ResponseEntity<Response> addProjectMembers(@PathVariable("project-id") Long projectId,
                                                     @RequestBody @Valid List<ProjectMemberRequestDto> projectMemberRequestDtoList) {
        projectMemberService.addProjectMembers(projectId, projectMemberRequestDtoList);
        return ResponseEntity.ok().body(new Response("OK"));
    }

    @PutMapping
    public ResponseEntity<Response> modifyProjectMembers(@PathVariable("project-id") Long projectId,
                                                         @RequestBody @Valid List<ProjectMemberRequestDto> projectMemberRequestDtoList) {
        projectMemberService.modifyProjectMembers(projectId, projectMemberRequestDtoList);
        return ResponseEntity.ok().body(new Response("OK"));
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteProjectMembers(@PathVariable("project-id") Long projectId,
                                                         @RequestBody @Valid List<ProjectMemberDeleteRequestDto>
                                                                 projectMemberDeleteRequestDtoList) {
        projectMemberService.deleteProjectMembers(projectId, projectMemberDeleteRequestDtoList);
        return ResponseEntity.ok().body(new Response("OK"));
    }
}
