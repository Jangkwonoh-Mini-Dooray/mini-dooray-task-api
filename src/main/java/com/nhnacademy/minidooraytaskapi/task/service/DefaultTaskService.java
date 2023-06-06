package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundMilestoneException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.milestone.repository.MilestoneRepository;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.task.dto.PostTaskDto;
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
    public List<TaskDto> getAllByProjectId(Long projectId) {
        return taskRepository.getTasks(projectId);
    }
    @Override
    @Transactional(readOnly = true)
    public TaskDto getTaskByTaskIdAndProjectId(Long taskId, Long projectId) {
        return taskRepository.getTask(taskId, projectId);
    }
    @Override
    public Long postTask(PostTaskDto postTaskDto, Long projectId) {
        Task task = new Task();
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
