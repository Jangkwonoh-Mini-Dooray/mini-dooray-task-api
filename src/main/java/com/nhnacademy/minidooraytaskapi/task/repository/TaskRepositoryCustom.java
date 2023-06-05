package com.nhnacademy.minidooraytaskapi.task.repository;

import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TaskRepositoryCustom {
    List<TaskDto> getTasks(Long projectId);

    TaskDto getTask(Long projectId, Long taskId);
}
