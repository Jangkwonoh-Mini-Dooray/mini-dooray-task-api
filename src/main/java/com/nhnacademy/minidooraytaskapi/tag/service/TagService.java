package com.nhnacademy.minidooraytaskapi.tag.service;

import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagIdDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TaskTagRequestDto;

import java.util.List;

public interface TagService {
    List<TagDto> getTags(Long projectId);
    List<TagDto> getTags(Long projectId, Long taskId);
    TagIdDto postTagTask(TaskTagRequestDto taskTagRequestDto, Long projectId);
    TagIdDto putTagTask(TaskTagRequestDto taskTagRequestDto, Long projectId, Long tagId);
    void deleteTag(Long tagId);
}
