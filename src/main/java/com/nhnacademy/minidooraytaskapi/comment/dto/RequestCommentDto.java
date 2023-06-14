package com.nhnacademy.minidooraytaskapi.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCommentDto {
    private String commentWriterMemberId;
    private String commentContent;
}
