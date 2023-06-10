package com.nhnacademy.minidooraytaskapi.commentmention.service;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.entity.CommentMention;
import com.nhnacademy.minidooraytaskapi.commentmention.repository.CommentMentionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommentMentionService implements CommentMentionService {
    private final CommentMentionRepository commentMentionRepository;
    //private final CommentRepository commentRepository;

    @Override
    public List<CommentMentionResponseDto> getCommentMentions(Long commentId) {
        return commentMentionRepository.getCommentMentions(commentId);
    }

    @Override
    public void createCommentMention(Long commentId, CommentMentionRequestDto commentMentionRequestDto) {
//        CommentMention commentMention = new CommentMention(new CommentMention.Pk(commentMentionRequestDto.getTargetMemberId(),commentId));
//        return commentMentionRepository.save();
    }
}
