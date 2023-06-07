package com.nhnacademy.minidooraytaskapi.get_project.controller;

import com.nhnacademy.minidooraytaskapi.get_project.dto.TargetMemberIdDto;
import com.nhnacademy.minidooraytaskapi.get_project.service.GetProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projets/{project-id}/members")
@RequiredArgsConstructor
public class GetProjectController {
    private final GetProjectService getProjectService;

    @GetMapping
    public ResponseEntity<List<TargetMemberIdDto>> getTargetMembers(@PathVariable("project-id") Long projectId) {
        List<TargetMemberIdDto> targetMembers = getProjectService.getTargetMembers(projectId);
        return ResponseEntity.ok().body(targetMembers);
    }

}
