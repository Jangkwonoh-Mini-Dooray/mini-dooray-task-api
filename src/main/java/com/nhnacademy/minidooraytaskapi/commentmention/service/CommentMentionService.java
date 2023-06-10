package com.nhnacademy.minidooraytaskapi.commentmention.service;

import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;

import java.util.List;

public interface CommentMentionService {
    List<CommentMentionResponseDto> getCommentMentions(Long commentId);
    void createCommentMention(Long commentId, CommentMentionRequestDto commentMentionRequestDto);
}
