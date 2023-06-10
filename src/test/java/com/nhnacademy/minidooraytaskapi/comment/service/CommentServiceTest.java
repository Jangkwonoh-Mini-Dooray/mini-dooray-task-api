package com.nhnacademy.minidooraytaskapi.comment.service;

import com.nhnacademy.minidooraytaskapi.comment.dto.CommentIdDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.RequestCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.comment.repository.CommentRepository;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundCommentException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundTaskException;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Comment : Service 테스트")
class CommentServiceTest {
    private AutoCloseable closeable;
    @Autowired
    CommentService commentService;
    @MockBean
    CommentRepository commentRepository;
    @MockBean
    TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeMock() throws Exception {
        closeable.close();
    }


    @Test
    @DisplayName("업무에 해당하는 댓글 가져오는 Service")
    void getComment() {
        ResponseCommentDto comment = new ResponseCommentDto(1L, "naht94", "hahaha");
        ResponseCommentDto comment2 = new ResponseCommentDto();
        comment2.setCommentId(2L);
        comment2.setCommentWriterMemberId("nami");
        comment2.setComment("hehehe");

        given(commentRepository.getCommentByTaskId(anyLong()))
                .willReturn(List.of(comment, comment2));

        List<ResponseCommentDto> comments = commentService.getComment(1L);

        Assertions.assertThat(comments).hasSize(2);
        Assertions.assertThat(comments.get(0)).isEqualTo(comment);
    }

    @Test
    @DisplayName("업무에 댓글 생성하는 Service #실패 해당하는 업무 없음")
    void postComment() {
        Comment comment = new Comment();
        RequestCommentDto requestCommentDto = new RequestCommentDto();
        ReflectionTestUtils.setField(comment, "commentId", 1L);
        given(taskRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        assertThrows(NotFoundTaskException.class, () -> commentService.postComment(requestCommentDto, 1L));
    }

    @Test
    @DisplayName("업무에 댓글 생성하는 Service #성공")
    void postComment2() {
        Comment comment = new Comment();
        RequestCommentDto requestCommentDto = new RequestCommentDto();
        Task task = new Task();
        ReflectionTestUtils.setField(task, "taskId", 1L);
        ReflectionTestUtils.setField(comment, "commentId", 1L);
        given(taskRepository.findById(anyLong()))
                .willReturn(Optional.of(task));
        given(commentRepository.save(any()))
                .willReturn(comment);

        CommentIdDto commentIdDto = commentService.postComment(requestCommentDto, 1L);
        Assertions.assertThat(commentIdDto.getCommentId()).isEqualTo(comment.getCommentId());
    }

    @Test
    @DisplayName("업무에 댓글 수정하는 Service #실패 해당하는 댓글 없음")
    void putComment() {
        RequestCommentDto requestCommentDto = new RequestCommentDto();
        given(commentRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        assertThrows(NotFoundCommentException.class, () -> commentService.putComment(requestCommentDto, 1L));
    }

    @Test
    @DisplayName("업무에 댓글 수정하는 Service #성공")
    void putComment2() {
        RequestCommentDto requestCommentDto = new RequestCommentDto();
        Comment comment = new Comment();
        ReflectionTestUtils.setField(comment, "commentId", 1L);
        given(commentRepository.findById(anyLong()))
                .willReturn(Optional.of(comment));
        given(commentRepository.save(any()))
                .willReturn(comment);
        requestCommentDto.setComment("update!");
        CommentIdDto commentIdDto = commentService.putComment(requestCommentDto, 2L);
        assertThat(commentIdDto.getCommentId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("댓글 삭제하는 Service #실패 댓글 id 없음")
    void deleteComment() {
        given(commentRepository.existsById(anyLong()))
                .willReturn(false);
        assertThrows(NotFoundCommentException.class, () -> commentService.deleteComment(1L));
    }

    @Test
    @DisplayName("댓글 삭제하는 Service #성공")
    void deleteComment2() {
        given(commentRepository.existsById(anyLong()))
                .willReturn(true);
        doNothing().when(commentRepository).deleteById(anyLong());
        commentService.deleteComment(1L);
        verify(commentRepository, times(1)).deleteById(1L);
    }
}