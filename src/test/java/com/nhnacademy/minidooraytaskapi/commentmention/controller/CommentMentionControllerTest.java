package com.nhnacademy.minidooraytaskapi.commentmention.controller;

import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.service.CommentMentionService;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentMentionController.class)
@DisplayName("CommentMention : Controller 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentMentionControllerTest {
    @MockBean
    CommentMentionService commentMentionService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    @DisplayName("댓글 내 멘션 전체 조회")
    void getCommentMentions() throws Exception {
        Long commentId = 1L;
        List<CommentMentionResponseDto> commentMentionResponseDtoList = new ArrayList<>();
        CommentMentionResponseDto commentMentionResponseDto = new CommentMentionResponseDto("test");
        commentMentionResponseDtoList.add(commentMentionResponseDto);

        when(commentMentionService.getCommentMentions(commentId))
                .thenReturn(commentMentionResponseDtoList);

        mockMvc.perform(get("/mentions/{comment-id}", commentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    @DisplayName("댓글 내 멘션 생성")
    void createCommentMention() throws Exception {
        Long commentId = 1L;
        List<String> targetMemberIdList = new ArrayList<>();
        targetMemberIdList.add("test");
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto(targetMemberIdList);

        doNothing().when(commentMentionService).createCommentMention(commentId, commentMentionRequestDto);

        mockMvc.perform(post("/mentions/{comment-id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentMentionRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", equalTo("OK")));
    }

    @Test
    @Order(3)
    @DisplayName("댓글 내 멘션 수정")
    void modifyCommentMention() throws Exception {
        Long commentId = 1L;
        List<String> targetMemberIdList = new ArrayList<>();
        targetMemberIdList.add("test");
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto(targetMemberIdList);

        doNothing().when(commentMentionService).modifyCommentMention(commentId, commentMentionRequestDto);

        mockMvc.perform(put("/mentions/{comment-id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentMentionRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", equalTo("OK")));;
    }

    @Test
    @Order(4)
    @DisplayName("댓글 내 멘션 삭제")
    void deleteCommentMention() throws Exception {
        Long commentId = 1L;
        List<String> targetMemberIdList = new ArrayList<>();
        targetMemberIdList.add("test");
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto(targetMemberIdList);

        doNothing().when(commentMentionService).deleteCommentMention(commentId, commentMentionRequestDto);

        mockMvc.perform(delete("/mentions/{comment-id}", commentId)
                        .content(objectMapper.writeValueAsString(commentMentionRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", equalTo("OK")));;
    }
}