package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.task.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllByProjectId(Long projectId);
}
