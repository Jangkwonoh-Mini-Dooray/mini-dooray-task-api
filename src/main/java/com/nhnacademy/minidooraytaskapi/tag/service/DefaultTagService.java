package com.nhnacademy.minidooraytaskapi.tag.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagIdDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagRequestDto;
import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import com.nhnacademy.minidooraytaskapi.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultTagService implements TagService {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    @Override
    public List<TagDto> getTags(Long projectId) {
        return tagRepository.getTagByProjectId(projectId);
    }
    @Override
    public List<TagDto> getTags(Long projectId, Long taskId) {
        return tagRepository.getTagByProjectIdAndTaskId(projectId, taskId);
    }

    @Override
    public TagIdDto postTag(TagRequestDto tagRequestDto, Long projectId) {
        Tag tag = new Tag();
        tag.setName(tagRequestDto.getName());

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(projectId));
        tag.setProject(project);

        Tag save = tagRepository.save(tag);

        TagIdDto result = new TagIdDto();
        result.setTagId(save.getTagId());
        return result;
    }
}
