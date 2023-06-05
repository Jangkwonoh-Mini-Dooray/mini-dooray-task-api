package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService{
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getAllByProjectId(Long projectId) {
        return taskRepository.getAllByProjectProjectId(projectId);
    }
}
