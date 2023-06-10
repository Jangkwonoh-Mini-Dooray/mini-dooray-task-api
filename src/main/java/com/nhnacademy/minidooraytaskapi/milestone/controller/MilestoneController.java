package com.nhnacademy.minidooraytaskapi.milestone.controller;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneIdDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneRequestDto;
import com.nhnacademy.minidooraytaskapi.milestone.service.MilestoneService;
import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.util.ValidateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/milestones")
@RequiredArgsConstructor
public class MilestoneController implements ValidateParam {
    private final MilestoneService milestoneService;

    @GetMapping("projects/{project-id}")
    public ResponseEntity<List<MilestoneDto>> getMilestones(@PathVariable("project-id") Long projectId) {
        List<MilestoneDto> result = milestoneService.getMilestones(projectId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{milestone-id}")
    public ResponseEntity<MilestoneDto> getMilestone(@PathVariable("milestone-id") Long milestoneId) {
        MilestoneDto result = milestoneService.getMilestone(milestoneId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/projects/{project-id}")
    public ResponseEntity<MilestoneIdDto> createMilestone(@PathVariable("project-id") Long projectId,
                                                               @RequestBody @Valid MilestoneRequestDto milestoneRequestDto,
                                                               BindingResult bindingResult) {
        validate(bindingResult);
        MilestoneIdDto result = milestoneService.createMilestone(projectId, milestoneRequestDto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("{milestone-id}")
    public ResponseEntity<MilestoneIdDto> modifyMilestone(@PathVariable("milestone-id") Long milestoneId,
                                                          @RequestBody @Valid MilestoneRequestDto milestoneRequestDto,
                                                          BindingResult bindingResult) {
        validate(bindingResult);
        MilestoneIdDto result = milestoneService.modifyMilestone(milestoneId, milestoneRequestDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("{milestone-id}")
    public ResponseEntity<Response> deleteMilestone(@PathVariable("milestone-id") Long milestoneId) {
        milestoneService.deleteMilestone(milestoneId);
        return ResponseEntity.ok().body(new Response("OK"));
    }
}
