package com.nhnacademy.minidooraytaskapi.projectstatus.controller;

import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusIdDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.service.ProjectStatusService;
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
@RequestMapping("/project-status")
@RequiredArgsConstructor
public class ProjectStatusController implements ValidateParam {
    private final ProjectStatusService projectStatusService;
    @GetMapping
    public ResponseEntity<List<ProjectStatusDto>> getProjectStatuses() {
        return new ResponseEntity<>(projectStatusService.getProjectStatuses(), HttpStatus.OK);
    }

    @GetMapping("/{projectStatusId}")
    public ResponseEntity<ProjectStatusNameDto> getProjectStatus(@PathVariable int projectStatusId) {
        return ResponseEntity.ok(projectStatusService.getProjectStatus(projectStatusId));
    }

    @PostMapping
    public ResponseEntity<ProjectStatusIdDto> createProjectStatus(@Valid @RequestBody ProjectStatusDto projectStatusDto,
                                                           BindingResult bindingResult) {
        validate(bindingResult);
        ProjectStatusIdDto responseDto = projectStatusService.createProjectStatus(projectStatusDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{projectStatusId}")
    public ResponseEntity<ProjectStatusIdDto> updateProjectStatus(@PathVariable int projectStatusId, @Valid @RequestBody ProjectStatusNameDto projectStatusNameDto,
                                                           BindingResult bindingResult) {
        validate(bindingResult);
        ProjectStatusIdDto responseDto = projectStatusService.updateProjectStatus(projectStatusId, projectStatusNameDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{projectStatusId}")
    public ResponseEntity<Response> deleteMember(@PathVariable int projectStatusId) {
        projectStatusService.deleteProjectStatus(projectStatusId);
        return ResponseEntity.ok(new Response("OK"));
    }
}
