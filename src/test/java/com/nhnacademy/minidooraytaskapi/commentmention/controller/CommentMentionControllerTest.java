package com.nhnacademy.minidooraytaskapi.commentmention.controller;

import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.service.CommentMentionService;
import com.nhnacademy.minidooraytaskapi.milestone.controller.MilestoneController;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneIdDto;
import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneRequestDto;
import com.nhnacademy.minidooraytaskapi.milestone.service.MilestoneService;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
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
        List<CommentMentionRequestDto> commentMentionRequestDtoList = new ArrayList<>();
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto("test");
        commentMentionRequestDtoList.add(commentMentionRequestDto);

        doNothing().when(commentMentionService).createCommentMention(commentId, commentMentionRequestDtoList);

        mockMvc.perform(post("/mentions/{comment-id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentMentionRequestDtoList)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void modifyCommentMention() throws Exception {
        Long commentId = 1L;
        List<CommentMentionRequestDto> commentMentionRequestDtoList = new ArrayList<>();
        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto("test");
        commentMentionRequestDtoList.add(commentMentionRequestDto);

        doNothing().when(commentMentionService).modifyCommentMention(commentId, commentMentionRequestDtoList);

        mockMvc.perform(put("/mentions/{comment-id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentMentionRequestDtoList)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCommentMention() throws Exception {
//        Long commentId = 1L;
//        List<CommentMentionRequestDto> commentMentionRequestDtoList = new ArrayList<>();
//        CommentMentionRequestDto commentMentionRequestDto = new CommentMentionRequestDto("test");
//        commentMentionRequestDtoList.add(commentMentionRequestDto);
//
//        doNothing().when(commentMentionService).modifyCommentMention(commentId, commentMentionRequestDtoList);
//
//        mockMvc.perform(delete("/mentions/{comment-id}", commentId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
    }
}