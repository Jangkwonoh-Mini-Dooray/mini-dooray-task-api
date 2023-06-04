package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService{
    private TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByProjectId(Long projectId) {
        return taskRepository.getAllByProject_ProjectId(projectId);
    }
}
