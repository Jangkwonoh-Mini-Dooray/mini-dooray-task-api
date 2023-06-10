package com.nhnacademy.minidooraytaskapi.comment.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ResponseCommentDto {
    private Long commentId;
    private String commentWriterMemberId;
    private String comment;

    public ResponseCommentDto(Long commentId, String commentWriterMemberId, String comment) {
        this.commentId = commentId;
        this.commentWriterMemberId = commentWriterMemberId;
        this.comment = comment;
    }
}
