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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommentMentionService implements CommentMentionService {
    private final CommentMentionRepository commentMentionRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentMentionResponseDto> getCommentMentions(Long commentId) {
        return commentMentionRepository.getCommentMentions(commentId);
    }

    @Override
    public void createCommentMention(Long commentId, List<CommentMentionRequestDto> commentMentionRequestDtoList) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentException(commentId));
        List<CommentMention> commentMentionList = convertToCommentMention(comment, commentMentionRequestDtoList);
        commentMentionRepository.saveAllAndFlush(commentMentionList);
    }

    @Override
    public void modifyCommentMention(Long commentId, List<CommentMentionRequestDto> commentMentionRequestDtoList) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentException(commentId));
        List<CommentMention> commentMentionList = convertToCommentMention(comment, commentMentionRequestDtoList);
        commentMentionRepository.saveAllAndFlush(commentMentionList);
    }

    @Override
    public void deleteCommentMention(Long commentId, List<CommentMentionRequestDto> commentMentionRequestDtoList) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentException(commentId));
        List<CommentMention> commentMentionList = convertToCommentMention(comment, commentMentionRequestDtoList);
        commentMentionRepository.deleteAll(commentMentionList);
    }

    public List<CommentMention> convertToCommentMention(Comment comment, List<CommentMentionRequestDto> commentMentionRequestDtoList) {
        List<CommentMention> commentMentionList = new ArrayList<>();
        for (CommentMentionRequestDto dto : commentMentionRequestDtoList) {
//            CommentMention commentMention = new CommentMention(new CommentMention.Pk(dto.getTargetMemberId(), comment.getCommentId()), comment);
//            commentMentionList.add(commentMention);
        }
        return commentMentionList;
    }
}
