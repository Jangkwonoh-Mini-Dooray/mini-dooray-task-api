package com.nhnacademy.minidooraytaskapi.projectauthority.controller;

import com.nhnacademy.minidooraytaskapi.exception.ValidationFailedException;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityIdDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import com.nhnacademy.minidooraytaskapi.projectauthority.service.ProjectAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project-authority")
@RequiredArgsConstructor
public class ProjectAuthorityController {
    private final ProjectAuthorityService projectAuthorityService;
    @GetMapping
    public ResponseEntity<List<ProjectAuthorityDto>> getProjectAuthorities() {
        return new ResponseEntity<>(projectAuthorityService.getProjectAuthorities(), HttpStatus.OK);
    }

    @GetMapping("/{projectAuthorityId}")
    public ResponseEntity<ProjectAuthorityNameDto> getProjectAuthority(@PathVariable int projectAuthorityId) {
        return new ResponseEntity<>(projectAuthorityService.getProjectAuthority(projectAuthorityId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectAuthorityIdDto> createProjectAuthority(@Valid @RequestBody ProjectAuthorityDto projectAuthorityDto,
                                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        ProjectAuthority projectAuthority = projectAuthorityService.createProjectAuthority(projectAuthorityDto);
        ProjectAuthorityIdDto responseDto = new ProjectAuthorityIdDto(projectAuthority.getProjectAuthorityId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{projectAuthorityId}")
    public ResponseEntity<ProjectAuthorityIdDto> updateProjectAuthority(@PathVariable int projectAuthorityId, @Valid @RequestBody ProjectAuthorityNameDto projectAuthorityNameDto,
                                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        ProjectAuthority projectAuthority = projectAuthorityService.updateMember(projectAuthorityId, projectAuthorityNameDto);
        ProjectAuthorityIdDto responseDto = new ProjectAuthorityIdDto(projectAuthority.getProjectAuthorityId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{projectAuthorityId}")
    public ResponseEntity<Void> deleteProjectAuthority(@PathVariable int projectAuthorityId) {
        projectAuthorityService.deleteProjectAuthority(projectAuthorityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
