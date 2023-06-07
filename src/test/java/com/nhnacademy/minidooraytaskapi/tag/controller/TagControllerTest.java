package com.nhnacademy.minidooraytaskapi.tag.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagIdDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagRequestDto;
import com.nhnacademy.minidooraytaskapi.tag.service.TagService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TagController.class)
@DisplayName("Tag : Controller 테스트")
class TagControllerTest {
    @MockBean
    TagService tagService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("프로젝트에 존재하는 모든 태그 찾기")
    void getTags() throws Exception {
        TagDto tag = new TagDto();
        TagDto tag2 = new TagDto();
        tag.setTagId(1L);
        tag.setName("test1");
        tag2.setTagId(2L);
        tag2.setName("test2");

        when(tagService.getTags(anyLong()))
                .thenReturn(List.of(tag, tag2));

        mockMvc.perform(get("/projects/{project-id}/tags", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].tagId", equalTo(1)));
    }

    @Test
    @DisplayName("프로젝트 내 업무에 존재하는 모든 태그 찾기")
    void getTagsByTags() throws Exception {
        TagDto tag = new TagDto();
        TagDto tag2 = new TagDto();
        tag.setTagId(1L);
        tag.setName("test1");
        tag2.setTagId(2L);
        tag2.setName("test2");

        when(tagService.getTags(anyLong(), anyLong()))
                .thenReturn(List.of(tag, tag2));

        mockMvc.perform(get("/projects/{project-id}/tags/{task-id}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].tagId", equalTo(1)));
    }

    @Test
    @DisplayName("프로젝트 내 태그 생성 #실패")
    void createTags() throws Exception {
        TagRequestDto tagRequestDto = new TagRequestDto();
        tagRequestDto.setName("test");

        mockMvc.perform(post("/projects/{project-id}/tags", 1L))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("프로젝트 내 태그 생성 #성공")
    void createTags2() throws Exception {
        TagRequestDto tagRequestDto = new TagRequestDto();
        tagRequestDto.setName("test");

        TagIdDto tagIdDto = new TagIdDto();
        tagIdDto.setTagId(1L);

        when(tagService.postTag(any(), anyLong()))
                .thenReturn(tagIdDto);

        mockMvc.perform(post("/projects/{project-id}/tags", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"tagId\":1}"));
    }
}