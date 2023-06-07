package com.nhnacademy.minidooraytaskapi.tag.repository;

import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TagRepositoryCustom {
    List<TagDto> getTagByProjectId(Long projectId);
    List<TagDto> getTagByProjectIdAndTaskId(Long projectId, Long taskId);
}
