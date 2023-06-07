package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.task.dto.PostTaskDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAllByProjectId(Long projectId);
    TaskDto getTaskByTaskIdAndProjectId(Long taskId, Long projectId);
    Long postTask(PostTaskDto postTaskDto, Long projectId);
    Long putTask(PostTaskDto postTaskDto, Long projectId, Long taskId);
    void deleteTask(Long taskId);
    Long saveTask(PostTaskDto postTaskDto, Long projectId, Task task);
}
