package com.nhnacademy.minidooraytaskapi.comment_mention.entity;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comment_mention")
public class CommentMention {
    @EmbeddedId
    private Pk pk;

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "target_member_id")
        private Long targetMemberId;
        private Long commentId;
        private Long taskId;
    }

    @MapsId("commentId")
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @MapsId("taskId")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Comment task;
}
