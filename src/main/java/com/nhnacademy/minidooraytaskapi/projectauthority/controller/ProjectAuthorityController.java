package com.nhnacademy.minidooraytaskapi.projectauthority.controller;


import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityIdDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.service.ProjectAuthorityService;
import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.util.ValidateParam;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project-authority")
@RequiredArgsConstructor
public class ProjectAuthorityController implements ValidateParam {
    private final ProjectAuthorityService projectAuthorityService;

    @GetMapping
    public ResponseEntity<List<ProjectAuthorityDto>> getProjectAuthorities() {
        return ResponseEntity.ok(projectAuthorityService.getProjectAuthorities());
    }

    @GetMapping("/{project-authority-id}")
    public ResponseEntity<ProjectAuthorityNameDto> getProjectAuthority(@PathVariable("project-authority-id") int id) {
        return ResponseEntity.ok(projectAuthorityService.getProjectAuthority(id));
    }

    @PostMapping
    public ResponseEntity<ProjectAuthorityIdDto> postProjectAuthority(@RequestBody @Valid ProjectAuthorityDto projectAuthorityDto, BindingResult bindingResult) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectAuthorityService.postProjectAuthority(projectAuthorityDto));
    }

    @PutMapping("/{project-authority-id}")
    public ResponseEntity<ProjectAuthorityIdDto> putProjectAuthority(@RequestBody @Valid ProjectAuthorityDto projectAuthorityDto, BindingResult bindingResult, @PathVariable("project-authority-id") int id) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectAuthorityService.putProjectAuthority(projectAuthorityDto, id));
    }

    @DeleteMapping("/{project-authority-id}")
    public ResponseEntity<Response> deleteProjectAuthority(@PathVariable("project-authority-id") int id) {
        projectAuthorityService.deleteProjectAuthority(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("OK"));
    }
}
