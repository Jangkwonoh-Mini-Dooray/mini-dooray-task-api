package com.nhnacademy.minidooraytaskapi.comment.entity;

import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @Column(name = "comment_writer_member_id")
    private String commentWriterMemberId;
    private String comment;
    @Column(name = "created_at")
    private LocalDateTime postTime;

    public void save(Task task, String commentWriterMemberId, String comment, LocalDateTime postTime) {
        this.task = task;
        this.commentWriterMemberId = commentWriterMemberId;
        this.comment = comment;
        this.postTime = postTime;
    }

    public void update(String comment, LocalDateTime postTime) {
        this.comment = comment;
        this.postTime = postTime;
    }
}
