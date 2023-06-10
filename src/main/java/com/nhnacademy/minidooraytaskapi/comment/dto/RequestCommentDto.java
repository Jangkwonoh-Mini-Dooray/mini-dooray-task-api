package com.nhnacademy.minidooraytaskapi.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCommentDto {
    private String commentWriterMemberId;
    private String comment;
}
