package com.nhnacademy.minidooraytaskapi.tag.service;

import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagIdDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagRequestDto;

import java.util.List;

public interface TagService {
    List<TagDto> getTags(Long projectId);
    List<TagDto> getTags(Long projectId, Long taskId);
    TagIdDto postTag(TagRequestDto tagRequestDto, Long projectId);
    TagIdDto putTag(TagRequestDto tagRequestDto, Long projectId, Long tagId);
}
