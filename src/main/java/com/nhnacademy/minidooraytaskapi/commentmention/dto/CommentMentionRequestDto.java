package com.nhnacademy.minidooraytaskapi.commentmention.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentMentionRequestDto {
    private Long targetMemberId;
}
