package com.nhnacademy.minidooraytaskapi.tag.repository;

import com.nhnacademy.minidooraytaskapi.get_tag.entity.QGetTag;
import com.nhnacademy.minidooraytaskapi.project.entity.QProject;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.entity.QTag;
import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import com.nhnacademy.minidooraytaskapi.task.entity.QTask;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TagRepositoryImpl extends QuerydslRepositorySupport implements TagRepositoryCustom {
    public TagRepositoryImpl() {
        super(Tag.class);
    }

    @Override
    public List<TagDto> getTagByProjectId(Long projectId) {
        QTag tag = QTag.tag;
        QProject project = QProject.project;

        return from(tag)
                .innerJoin(tag.project, project)
                .select(Projections.bean(TagDto.class,
                        tag.tagId,
                        tag.name))
                .where(project.projectId.eq(projectId))
                .fetch();
    }

    @Override
    public List<TagDto> getTagByProjectIdAndTaskId(Long projectId, Long taskId) {
        QTag tag = QTag.tag;
        QGetTag getTag = QGetTag.getTag;
        QProject project = QProject.project;
        QTask task = QTask.task;
        return from(getTag)
                .innerJoin(getTag.tag, tag)
                .innerJoin(tag.project, project)
                .innerJoin(getTag.task, task)
                .select(Projections.bean(TagDto.class,
                        tag.tagId,
                        tag.name))
                .where(project.projectId.eq(projectId), task.taskId.eq(taskId))
                .fetch();
    }
}
