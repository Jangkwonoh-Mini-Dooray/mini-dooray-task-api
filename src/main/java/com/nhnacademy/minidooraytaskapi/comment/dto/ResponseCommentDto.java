package com.nhnacademy.minidooraytaskapi.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseCommentDto {
    private Long commentId;
    private String commentWriterMemberId;
    private String comment;
}
