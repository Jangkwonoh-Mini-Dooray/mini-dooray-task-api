package com.nhnacademy.minidooraytaskapi.commentmention.repository;

import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.entity.CommentMention;
import com.nhnacademy.minidooraytaskapi.commentmention.entity.QCommentMention;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CommentMentionRepositoryImpl extends QuerydslRepositorySupport implements CommentMentionRepositoryCustom {
    public CommentMentionRepositoryImpl() {
        super(CommentMention.class);
    }

    @Override
    public List<CommentMentionResponseDto> getCommentMentions(Long commentId) {
        QCommentMention commentMention = QCommentMention.commentMention;

        return from(commentMention)
                .where(commentMention.comment.commentId.eq(commentId))
                .select(Projections.constructor(CommentMentionResponseDto.class,
                        commentMention.pk.targetMemberId))
                .fetch();
    }
}
