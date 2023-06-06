package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.exception.PostTaskDtoException;
import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.task.dto.PostTaskDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskIdDto;
import com.nhnacademy.minidooraytaskapi.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projections/{project-id}/posts")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable("project-id") Long projectId) {
        List<TaskDto> allTasks = taskService.getAllByProjectId(projectId);
        return ResponseEntity.ok().body(allTasks);
    }

    @GetMapping("/{task-id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("project-id") Long projectId, @PathVariable("task-id") Long taskId) {
        TaskDto target = taskService.getTaskByTaskIdAndProjectId(projectId, taskId);
        return ResponseEntity.ok().body(target);
    }

    @PostMapping
    public ResponseEntity<TaskIdDto> createTask(@RequestBody @Valid PostTaskDto postTaskDto, BindingResult bindingResult, @PathVariable("project-id") Long projectId) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            String errorMessage = errors.get(0).getDefaultMessage();
            throw new PostTaskDtoException(errorMessage);
        }
        TaskIdDto taskIdDto = new TaskIdDto();
        taskIdDto.setTaskId(taskService.postTask(postTaskDto, projectId));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskIdDto);
    }
}
