package com.nhnacademy.minidooraytaskapi.comment.service;

import com.nhnacademy.minidooraytaskapi.comment.dto.CommentIdDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.RequestCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;

import java.util.List;

public interface CommentService {
    List<ResponseCommentDto> getComment(Long taskId);
    CommentIdDto postComment(RequestCommentDto requestCommentDto, Long taskId);
    CommentIdDto putComment(RequestCommentDto requestCommentDto, Long commentId);

    void deleteComment(Long commentId);
}
