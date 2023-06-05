//package com.nhnacademy.minidooraytaskapi.project.service;
//
//import com.nhnacademy.minidooraytaskapi.project.dto.ProjectDto;
//import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DefaultProjectService implements ProjectService {
//    private final ProjectRepository projectRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<ProjectDto> getProjects(String memberId) {
//        return projectRepository.findAllProjectDto(memberId);
//    }
//}
