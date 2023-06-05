package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAllByProjectId(Long projectId);
}
