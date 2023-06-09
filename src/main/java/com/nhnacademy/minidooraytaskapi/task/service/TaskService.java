package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.task.dto.TaskRequestDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> getTasks(Long projectId);
    TaskDto getTask(Long taskId, Long projectId);
    Long postTask(TaskRequestDto postTaskDto, Long projectId);
    Long putTask(TaskRequestDto postTaskDto, Long projectId, Long taskId);
    void deleteTask(Long taskId);
    Long saveTask(TaskRequestDto postTaskDto, Long projectId, Task task);
}
