package com.nhnacademy.minidooraytaskapi.commentmention.service;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.comment.repository.CommentRepository;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.entity.CommentMention;
import com.nhnacademy.minidooraytaskapi.commentmention.repository.CommentMentionRepository;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundCommentException;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("commentMention : Service 테스트")
class DefaultCommentMentionServiceTest {
    @InjectMocks
    DefaultCommentMentionService commentMentionService;
    @Mock
    CommentMentionRepository commentMentionRepository;
    @Mock
    CommentRepository commentRepository;

    @Test
    @Order(1)
    @DisplayName("Comment ID 로 댓글 내 멘션 전체 조회")
    void testGetCommentMentions() {
        Long commentId = 1L;
        List<CommentMentionResponseDto> commentMentionResponseDtoList = new ArrayList<>();
        CommentMentionResponseDto commentMentionResponseDto = new CommentMentionResponseDto("test");
        commentMentionResponseDtoList.add(commentMentionResponseDto);

        given(commentMentionRepository.getCommentMentions(commentId))
                .willReturn(commentMentionResponseDtoList);

        List<CommentMentionResponseDto> actual =  commentMentionService.getCommentMentions(commentId);
        assertThat(actual).hasSize(1);
    }

    @Test
    @Order(2)
    @DisplayName("댓글 내 멘션 생성")
    void testCreateCommentMention() {
        Long commentId = 1L;
        Task task = new Task();
        Comment comment = new Comment();
        comment.save(task, "test", "test");
        CommentMention commentMention = new CommentMention();
        List<CommentMention> commentMentionList = new ArrayList<>();
        commentMentionList.add(commentMention);

        List<String> targetMemberIdList = new ArrayList<>();
        targetMemberIdList.add("test");
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto(targetMemberIdList);

        given(commentRepository.findById(commentId))
                .willReturn(Optional.of(comment));
        given(commentMentionRepository.saveAllAndFlush(any()))
                .willReturn(commentMentionList);

        commentMentionService.putCommentMention(commentId, commentMentionRequestDto);
        verify(commentMentionRepository).saveAllAndFlush(anyList());
    }

    @Test
    @Order(3)
    @DisplayName("댓글 내 멘션 수정")
    void testModifyCommentMention() {
        Long commentId = 1L;
        Task task = new Task();
        Comment comment = new Comment();
        comment.save(task, "test", "test");
        CommentMention commentMention = new CommentMention();
        List<CommentMention> commentMentionList = new ArrayList<>();
        commentMentionList.add(commentMention);

        List<String> targetMemberIdList = new ArrayList<>();
        targetMemberIdList.add("test");
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto(targetMemberIdList);

        given(commentRepository.findById(commentId))
                .willReturn(Optional.of(comment));
        given(commentMentionRepository.saveAllAndFlush(any()))
                .willReturn(commentMentionList);

        commentMentionService.putCommentMention(commentId, commentMentionRequestDto);
        verify(commentMentionRepository).saveAllAndFlush(anyList());
    }

    @Test
    @Order(4)
    @DisplayName("댓글 내 멘션 삭제")
    void testDeleteCommentMention() {
        Long commentId = 1L;
        Task task = new Task();
        Comment comment = new Comment();
        comment.save(task, "test", "test");
        CommentMention commentMention = new CommentMention();
        List<CommentMention> commentMentionList = new ArrayList<>();
        commentMentionList.add(commentMention);

        List<String> targetMemberIdList = new ArrayList<>();
        targetMemberIdList.add("test");
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto(targetMemberIdList);

        given(commentRepository.findById(commentId))
                .willReturn(Optional.of(comment));

        commentMentionService.deleteCommentMention(commentId, commentMentionRequestDto);
        verify(commentMentionRepository).deleteAll(anyList());
    }

    @Test
    @Order(5)
    @DisplayName("댓글 내 멘션 생성 실패 : 댓글이 없는 경우")
    void createCommentMentionFail() {
        Long commentId = 1L;
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto();

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundCommentException.class, () ->
                commentMentionService.putCommentMention(commentId, commentMentionRequestDto));
    }
}