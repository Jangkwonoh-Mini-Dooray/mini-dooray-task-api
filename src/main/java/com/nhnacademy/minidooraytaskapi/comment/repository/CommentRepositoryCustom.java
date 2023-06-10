package com.nhnacademy.minidooraytaskapi.comment.repository;

import com.nhnacademy.minidooraytaskapi.comment.dto.RequestCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;

import java.util.List;

public interface CommentRepositoryCustom {
    List<ResponseCommentDto> getCommentByTaskId(Long taskId);
}
