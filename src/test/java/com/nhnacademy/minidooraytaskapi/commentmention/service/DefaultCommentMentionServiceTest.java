package com.nhnacademy.minidooraytaskapi.commentmention.service;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.comment.repository.CommentRepository;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.entity.CommentMention;
import com.nhnacademy.minidooraytaskapi.commentmention.repository.CommentMentionRepository;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneIdDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneRequestDto;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.milestone.repository.MilestoneRepository;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("commentMention : Service 테스트")
class DefaultCommentMentionServiceTest {
    @Autowired
    CommentMentionService commentMentionService;
    @MockBean
    CommentMentionRepository commentMentionRepository;
    @MockBean
    CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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
        Task task = new Task();
        Comment comment = new Comment();
        comment.save(task, "test", "test", LocalDateTime.now());
        CommentMention commentMention = new CommentMention();
        List<CommentMention> commentMentionList = new ArrayList<>();
        commentMentionList.add(commentMention);

        List<CommentMentionRequestDto> commentMentionRequestDtoList = new ArrayList<>();
//        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto("test");
//        commentMentionRequestDtoList.add(commentMentionRequestDto);

        given(commentRepository.findById(anyLong()))
                .willReturn(Optional.of(comment));
        given(commentMentionRepository.saveAllAndFlush(any()))
                .willReturn(commentMentionList);

        //commentMentionService.createCommentMention(comment.getCommentId(), commentMentionRequestDtoList);
        //verify(commentMentionRepository).saveAllAndFlush(anyList());
    }

    @Test
    void testModifyCommentMention() {
    }

    @Test
    void testDeleteCommentMention() {

    }
}