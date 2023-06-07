package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundMilestoneException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundTaskException;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.milestone.repository.MilestoneRepository;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskRequestDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultTaskService implements TaskService{
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getTasks(Long projectId) {
        return taskRepository.getTasks(projectId);
    }
    @Override
    @Transactional(readOnly = true)
    public TaskDto getTask(Long taskId, Long projectId) {
        return taskRepository.getTask(taskId, projectId);
    }
    @Override
    public Long postTask(TaskRequestDto postTaskDto, Long projectId) {
        Task task = new Task();
        return saveTask(postTaskDto, projectId, task);
    }

    @Override
    public Long putTask(TaskRequestDto postTaskDto, Long projectId, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundTaskException(taskId));
        return saveTask(postTaskDto, projectId, task);
    }

    @Override
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundTaskException(taskId);
        }
        taskRepository.deleteById(taskId);
    }
    @Override
    public Long saveTask(TaskRequestDto postTaskDto, Long projectId, Task task) {
        task.setTitle(postTaskDto.getTitle());
        task.setTaskWriterMemberId(postTaskDto.getTaskWriterMemberId());

        Project targetProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(projectId));
        task.setProject(targetProject);

        Long milestoneId = postTaskDto.getMilestoneId();
        if (Objects.nonNull(milestoneId)) {
            Milestone targetMilestone = milestoneRepository.findById(milestoneId)
                    .orElseThrow(() -> new NotFoundMilestoneException(postTaskDto.getMilestoneId()));
            task.setMilestone(targetMilestone);
        }

        task.setContent(postTaskDto.getContent());
        Task result = taskRepository.saveAndFlush(task);
        return result.getTaskId();
    }
}
