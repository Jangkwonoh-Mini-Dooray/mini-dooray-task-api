package com.nhnacademy.minidooraytaskapi.milestone.controller;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects/{project-id}/milestones")
@RequiredArgsConstructor
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping
    public ResponseEntity<List<MilestoneDto>> getMilestones(@PathVariable("project-id") Long projectId) {
        List<MilestoneDto> result = milestoneService.getMilestones(projectId);
        return ResponseEntity.ok().body(result);
    }
}
