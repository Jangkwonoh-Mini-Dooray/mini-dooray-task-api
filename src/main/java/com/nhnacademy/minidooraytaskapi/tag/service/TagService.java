package com.nhnacademy.minidooraytaskapi.tag.service;

import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> getTags(Long projectId);
}
