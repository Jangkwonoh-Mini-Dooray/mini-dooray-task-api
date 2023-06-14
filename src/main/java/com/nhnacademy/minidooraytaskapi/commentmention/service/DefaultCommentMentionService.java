package com.nhnacademy.minidooraytaskapi.commentmention.service;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.comment.repository.CommentRepository;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.entity.CommentMention;
import com.nhnacademy.minidooraytaskapi.commentmention.repository.CommentMentionRepository;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundCommentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultCommentMentionService implements CommentMentionService {
    private final CommentMentionRepository commentMentionRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentMentionResponseDto> getCommentMentions(Long commentId) {
        return commentMentionRepository.getCommentMentions(commentId);
    }

    @Override
    public void putCommentMention(Long commentId, CommentMentionRequestDto commentMentionRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentException(commentId));
        List<CommentMention> commentMentionList = convertToCommentMention(comment, commentMentionRequestDto);
        commentMentionRepository.saveAllAndFlush(commentMentionList);
    }

    @Override
    public void deleteCommentMention(Long commentId, CommentMentionRequestDto commentMentionRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentException(commentId));
        List<CommentMention> commentMentionList = convertToCommentMention(comment, commentMentionRequestDto);
        commentMentionRepository.deleteAll(commentMentionList);
    }

    public List<CommentMention> convertToCommentMention(Comment comment, CommentMentionRequestDto commentMentionRequestDto) {
        List<CommentMention> commentMentionList = new ArrayList<>();
        List<String> targetMemberIdList = commentMentionRequestDto.getTargetMemberId();
        for (String targetMemberId : targetMemberIdList) {
            CommentMention commentMention = new CommentMention(new CommentMention.Pk(targetMemberId, comment.getCommentId()), comment);
            commentMentionList.add(commentMention);
        }
        return commentMentionList;
    }
}
