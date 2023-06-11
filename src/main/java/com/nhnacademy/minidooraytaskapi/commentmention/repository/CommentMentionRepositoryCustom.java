package com.nhnacademy.minidooraytaskapi.commentmention.repository;

import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommentMentionRepositoryCustom {
    List<CommentMentionResponseDto> getCommentMentions(Long commentId);
}
