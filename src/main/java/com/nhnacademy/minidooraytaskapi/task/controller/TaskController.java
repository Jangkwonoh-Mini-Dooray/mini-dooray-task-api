package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.exception.PostTaskDtoException;
import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.task.dto.PostTaskDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @GetMapping("/{project-id}/posts/{task-id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("project-id") Long projectId, @PathVariable("task-id") Long taskId) {
        TaskDto target = taskService.getTaskByTaskIdAndProjectId(projectId, taskId);
        return ResponseEntity.ok().body(target);
    }

    @PostMapping("/{project-id}/posts")
    public ResponseEntity<Response> createTask(@Valid PostTaskDto postTaskDto, BindingResult bindingResult, @PathVariable("project-id") Long projectId) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            String errorMessage = errors.get(0).getDefaultMessage();
            throw new PostTaskDtoException(errorMessage);
        }
        taskService.postTask(postTaskDto, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
