package com.nhnacademy.minidooraytaskapi.commentmention.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentMentionRequestDto {
    private List<String> targetMemberId;
}
