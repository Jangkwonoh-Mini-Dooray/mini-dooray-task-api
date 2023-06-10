package com.nhnacademy.minidooraytaskapi.projectmember.controller;

import com.nhnacademy.minidooraytaskapi.exception.ValidationFailedException;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberDeleteRequestDto;
import com.nhnacademy.minidooraytaskapi.projectmember.dto.ProjectMemberResponseDto;
import com.nhnacademy.minidooraytaskapi.projectmember.service.ProjectMemberService;
import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.util.ValidateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects/{project-id}/members")
@RequiredArgsConstructor
public class ProjectMemberController implements ValidateParam {
    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<ProjectMemberResponseDto>> getProjectMembers(@PathVariable("project-id") Long projectId) {
        List<ProjectMemberResponseDto> targetMembers = projectMemberService.getProjectMembers(projectId);
        return ResponseEntity.ok().body(targetMembers);
    }

    @PostMapping
    public ResponseEntity<Response> addProjectMembers(@PathVariable("project-id") Long projectId,
                                                     @RequestBody @Valid List<ProjectMemberResponseDto> projectMemberResponseDtoList,
                                                      BindingResult bindingResult) {
        validate(bindingResult);
        projectMemberService.addProjectMembers(projectId, projectMemberResponseDtoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("OK"));
    }

    @PutMapping
    public ResponseEntity<Response> modifyProjectMembers(@PathVariable("project-id") Long projectId,
                                                         @RequestBody @Valid List<ProjectMemberResponseDto> projectMemberResponseDtoList,
                                                         BindingResult bindingResult) {
        validate(bindingResult);
        projectMemberService.modifyProjectMembers(projectId, projectMemberResponseDtoList);
        return ResponseEntity.ok().body(new Response("OK"));
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteProjectMembers(@PathVariable("project-id") Long projectId,
                                                         @RequestBody @Valid List<ProjectMemberDeleteRequestDto>
                                                                 projectMemberDeleteRequestDtoList,
                                                         BindingResult bindingResult) {
        validate(bindingResult);
        projectMemberService.deleteProjectMembers(projectId, projectMemberDeleteRequestDtoList);
        return ResponseEntity.ok().body(new Response("OK"));
    }
}
