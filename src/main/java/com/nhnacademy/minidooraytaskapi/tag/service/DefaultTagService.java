package com.nhnacademy.minidooraytaskapi.tag.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundTagException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundTaskException;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagIdDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TaskTagRequestDto;
import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import com.nhnacademy.minidooraytaskapi.tag.repository.TagRepository;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import com.nhnacademy.minidooraytaskapi.tasktag.entity.TaskTag;
import com.nhnacademy.minidooraytaskapi.tasktag.repository.TaskTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultTagService implements TagService {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final TaskTagRepository taskTagRepository;
    private final TaskRepository taskRepository;
    @Override
    @Transactional(readOnly = true)
    public List<TagDto> getTags(Long projectId) {
        List<TagDto> tagByProjectId = tagRepository.getTagByProjectId(projectId);
        return tagByProjectId;
    }
    @Override
    @Transactional(readOnly = true)
    public List<TagDto> getTags(Long projectId, Long taskId) {
        return tagRepository.getTagByProjectIdAndTaskId(projectId, taskId);
    }

    @Override
    public TagIdDto postTagTask(TaskTagRequestDto taskTagRequestDto, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(projectId));
        Tag tag = new Tag(project);
        return save(taskTagRequestDto, tag);
    }

    @Override
    public TagIdDto putTagTask(TaskTagRequestDto taskTagRequestDto, Long projectId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new NotFoundTagException(tagId));
        return save(taskTagRequestDto, tag);
    }

    private TagIdDto save(TaskTagRequestDto taskTagRequestDto, Tag tag) {
        tag.setName(taskTagRequestDto.getName());

        Tag save = tagRepository.saveAndFlush(tag);

        if (Objects.nonNull(taskTagRequestDto.getTaskId())) {
            TaskTag taskTag = new TaskTag();
            Task task = taskRepository.findById(taskTagRequestDto.getTaskId())
                    .orElseThrow(() -> new NotFoundTaskException(taskTagRequestDto.getTaskId()));
            taskTag.update(new TaskTag.Pk(save.getTagId(), task.getTaskId()), save, task);
            taskTagRepository.save(taskTag);
        }

        TagIdDto result = new TagIdDto();
        result.setTagId(save.getTagId());
        return result;
    }

    @Override
    public void deleteTag(Long tagId) {
        if (!tagRepository.existsById(tagId)) {
            throw new NotFoundTagException(tagId);
        }
        tagRepository.deleteById(tagId);
    }
}
