package com.nhnacademy.minidooraytaskapi.commentmention.service;

import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;

import java.util.List;

public interface CommentMentionService {
    List<CommentMentionResponseDto> getCommentMentions(Long commentId);
    void createCommentMention(Long commentId, List<CommentMentionRequestDto> commentMentionRequestDtoList);
    void modifyCommentMention(Long commentId, List<CommentMentionRequestDto> commentMentionRequestDtoList);
    void deleteCommentMention(Long commentId, List<CommentMentionRequestDto> commentMentionRequestDtoList);
}
