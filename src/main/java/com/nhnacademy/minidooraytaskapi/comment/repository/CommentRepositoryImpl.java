package com.nhnacademy.minidooraytaskapi.comment.repository;

import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.comment.entity.QComment;
import com.nhnacademy.minidooraytaskapi.task.entity.QTask;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {
    public CommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public List<ResponseCommentDto> getCommentByTaskId(Long taskId) {
        QComment comment = QComment.comment;
        QTask task = QTask.task;
        return from(comment)
                .innerJoin(comment.task, task)
                .where(task.taskId.eq(taskId))
                .select(Projections.constructor(ResponseCommentDto.class,
                                comment.commentId,
                                comment.commentWriterMemberId,
                                comment.commentContent))
                .fetch();
    }
}
