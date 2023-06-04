package com.nhnacademy.minidooraytaskapi.comment.entity;

import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comment")
public class Comment {
    @EmbeddedId
    private Pk pk;
    @Column(name = "comment_writer_member_id")
    private String commentWriterMemberId;
    private String comment;

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "comment_id")
        private Long commentId;
        private Long taskId;
    }

    @MapsId("taskId")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
