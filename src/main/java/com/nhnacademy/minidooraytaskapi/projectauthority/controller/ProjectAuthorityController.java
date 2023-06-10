//package com.nhnacademy.minidooraytaskapi.projectauthority.controller;
//
//import com.nhnacademy.minidooraytaskapi.projectauthority.repository.ProjectAuthorityRepository;
//import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusDto;
//import com.nhnacademy.minidooraytaskapi.projectstatus.dto.ProjectStatusNameDto;
//import com.nhnacademy.minidooraytaskapi.projectstatus.service.ProjectStatusService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/project-authority")
//@RequiredArgsConstructor
//public class ProjectAuthorityController {
//    private final ;
//    @GetMapping
//    public ResponseEntity<List<ProjectStatusDto>> getProjectStatuses() {
//        return new ResponseEntity<>(projectStatusService.getProjectStatuses(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{projectStatusId}")
//    public ResponseEntity<ProjectStatusNameDto> getProjectStatus(@PathVariable int projectStatusId) {
//        return new ResponseEntity<>(projectStatusService.getProjectStatus(projectStatusId), HttpStatus.OK);
//    }
//}
