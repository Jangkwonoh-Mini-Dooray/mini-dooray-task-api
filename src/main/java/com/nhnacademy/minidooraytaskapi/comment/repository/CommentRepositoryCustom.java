package com.nhnacademy.minidooraytaskapi.comment.repository;

import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
@NoRepositoryBean
public interface CommentRepositoryCustom {
    List<ResponseCommentDto> getCommentByTaskId(Long taskId);
}
