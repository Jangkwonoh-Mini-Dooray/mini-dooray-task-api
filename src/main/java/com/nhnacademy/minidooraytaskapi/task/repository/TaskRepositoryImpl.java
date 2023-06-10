package com.nhnacademy.minidooraytaskapi.task.repository;

import com.nhnacademy.minidooraytaskapi.project.entity.QProject;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.QTask;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TaskRepositoryImpl extends QuerydslRepositorySupport implements TaskRepositoryCustom {
    public TaskRepositoryImpl() {
        super(Task.class);
    }

    @Override
    public List<TaskDto> getTasks(Long projectId) {
        QTask task = QTask.task;
        QProject project = QProject.project;
        return from(task).innerJoin(task.project, project)
                .where(task.project.projectId.eq(projectId))
                .select(Projections.constructor(TaskDto.class,
                        task.taskId,
                        task.taskWriterMemberId,
                        task.milestone,
                        task.title))
                .fetch();
    }

    @Override
    public TaskDto getTask(Long projectId, Long taskId) {
        QTask task = QTask.task;
        QProject project = QProject.project;
        return from(task).innerJoin(task.project, project)
                .select(Projections.constructor(TaskDto.class,
                        task.taskId,
                        task.taskWriterMemberId,
                        task.milestone,
                        task.title))
                .where(task.project.projectId.eq(projectId), task.taskId.eq(taskId))
                .fetchOne();
    }
}
