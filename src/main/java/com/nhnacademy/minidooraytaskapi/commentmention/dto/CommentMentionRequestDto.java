package com.nhnacademy.minidooraytaskapi.commentmention.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentMentionRequestDto {
    private String targetMemberId;
}
