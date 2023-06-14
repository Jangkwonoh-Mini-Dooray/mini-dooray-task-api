package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskRequestDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskIdDto;
import com.nhnacademy.minidooraytaskapi.task.service.TaskService;
import com.nhnacademy.minidooraytaskapi.util.ValidateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/projects")
@RequiredArgsConstructor
public class TaskController implements ValidateParam {
    private final TaskService taskService;

    @GetMapping("/{project-id}/posts")
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable("project-id") Long projectId) {
        List<TaskDto> allTasks = taskService.getTasks(projectId);
        return ResponseEntity.ok().body(allTasks);
    }

    @GetMapping("/{project-id}/posts/{task-id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("project-id") Long projectId, @PathVariable("task-id") Long taskId) {
        TaskDto target = taskService.getTask(projectId, taskId);
        return ResponseEntity.ok().body(target);
    }

    @PostMapping("/{project-id}/posts")
    public ResponseEntity<TaskIdDto> createTask(@RequestBody @Valid TaskRequestDto postTaskDto, BindingResult bindingResult, @PathVariable("project-id") Long projectId) {
        validate(bindingResult);
        TaskIdDto taskIdDto = new TaskIdDto(taskService.postTask(postTaskDto, projectId));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskIdDto);
    }

    @PutMapping("/{project-id}/posts/{task-id}")
    public ResponseEntity<TaskIdDto> modifyTask(@RequestBody @Valid TaskRequestDto postTaskDto, BindingResult bindingResult, @PathVariable("project-id") Long projectId, @PathVariable("task-id") Long taskId) {
        validate(bindingResult);
        TaskIdDto taskIdDto = new TaskIdDto(taskService.putTask(postTaskDto, projectId, taskId));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskIdDto);
    }

    @DeleteMapping(value = "/posts/{task-id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteTask(@PathVariable("task-id") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok(new Response("OK"));
    }
}
