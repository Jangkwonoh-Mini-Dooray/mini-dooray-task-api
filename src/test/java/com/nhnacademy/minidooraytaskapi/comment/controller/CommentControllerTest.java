package com.nhnacademy.minidooraytaskapi.comment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooraytaskapi.comment.dto.CommentIdDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.RequestCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.comment.service.CommentService;
import com.nhnacademy.minidooraytaskapi.milestone.controller.MilestoneController;
import com.nhnacademy.minidooraytaskapi.milestone.service.MilestoneService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@DisplayName("Comment : Controller 테스트")
class CommentControllerTest {
    @MockBean
    CommentService commentService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    @DisplayName("업무에 해당하는 댓글 가져오는 Controller #성공")
    void getComments() throws Exception {
        ResponseCommentDto comment = new ResponseCommentDto();
        ResponseCommentDto comment2 = new ResponseCommentDto();
        ReflectionTestUtils.setField(comment, "commentId", 1L);
        ReflectionTestUtils.setField(comment2, "commentId", 2L);

        when(commentService.getComment(anyLong()))
                .thenReturn(List.of(comment, comment2));

        mockMvc.perform(get("/task/{task-id}/comments", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].commentId", equalTo(1)));
    }

    @Test
    @DisplayName("업무에 댓글 생성하는 Controller #실패")
    void postComment() throws Exception {
        mockMvc.perform(post("/task/{task-id}/comments", 1L))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("업무에 댓글 생성하는 Controller #성공")
    void postComment2() throws Exception {
        RequestCommentDto requestCommentDto = new RequestCommentDto("It's me!", "what!");

        CommentIdDto commentIdDto = new CommentIdDto(1L);
        when(commentService.postComment(any(), anyLong()))
                .thenReturn(commentIdDto);

        mockMvc.perform(post("/task/{task-id}/comments", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestCommentDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.commentId", equalTo(1)));
    }

    @Test
    @DisplayName("업무에 댓글 수정하는 Controller #실패")
    void putComment() throws Exception {
        mockMvc.perform(put("/task/comments/{comment-id}", 1L))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("업무에 댓글 수정하는 Controller #성공")
    void putComment2() throws Exception {
        RequestCommentDto requestCommentDto = new RequestCommentDto("It's me!", "what!");

        CommentIdDto commentIdDto = new CommentIdDto(1L);
        when(commentService.putComment(any(), anyLong()))
                .thenReturn(commentIdDto);

        mockMvc.perform(put("/task/comments/{comment-id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestCommentDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.commentId", equalTo(1)));
    }

    @Test
    @DisplayName("업무에 댓글 삭제하는 Controller #성공")
    void deleteComment() throws Exception {
        doNothing().when(commentService).deleteComment(anyLong());

        mockMvc.perform(delete("/task/comments/{comment-id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("OK")));
    }
}