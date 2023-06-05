package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projections")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{project-id}/posts")
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable("project-id") Long projectId) {
        List<TaskDto> allTasks = taskService.getAllByProjectId(projectId);
        return ResponseEntity.ok().body(allTasks);
    }
}
