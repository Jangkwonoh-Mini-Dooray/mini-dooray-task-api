package com.nhnacademy.minidooraytaskapi.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
