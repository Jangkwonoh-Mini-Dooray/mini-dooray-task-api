package com.nhnacademy.minidooraytaskapi.project_member.controller;

import com.nhnacademy.minidooraytaskapi.project_member.dto.ProjectMemberRequestDto;
import com.nhnacademy.minidooraytaskapi.project_member.service.GetProjectService;
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
    private final GetProjectService getProjectService;

    @GetMapping
    public ResponseEntity<List<ProjectMemberRequestDto>> getTargetMembers(@PathVariable("project-id") Long projectId) {
        List<ProjectMemberRequestDto> targetMembers = getProjectService.getTargetMembers(projectId);
        return ResponseEntity.ok().body(targetMembers);
    }

    @PostMapping
    public ResponseEntity<Response> addTargetMembers(@PathVariable("project-id") Long projectId,
                                                     @RequestBody @Valid List<ProjectMemberRequestDto> targetMembers) {
        getProjectService.addTargetMembers(projectId, targetMembers);
        return ResponseEntity.ok().body(new Response("OK"));
    }


}
