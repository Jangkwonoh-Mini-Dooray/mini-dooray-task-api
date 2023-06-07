package com.nhnacademy.minidooraytaskapi.tag.repository;

import com.nhnacademy.minidooraytaskapi.project.entity.QProject;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.entity.QTag;
import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
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
}
