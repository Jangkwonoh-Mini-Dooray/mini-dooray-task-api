package com.nhnacademy.minidooraytaskapi.comment.entity;

import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "task_id")
    private Task task;
    @Column(name = "comment_writer_member_id")
    private String commentWriterMemberId;
    @Column(name = "comment")
    private String commentContent;
    @Column(name = "created_at")
    private LocalDateTime postTime;

    public void save(Task task, String commentWriterMemberId, String commentContent) {
        this.task = task;
        this.commentWriterMemberId = commentWriterMemberId;
        this.commentContent = commentContent;
        this.postTime = LocalDateTime.now();
    }

    public void update(String commentContent) {
        this.commentContent = commentContent;
        this.postTime = LocalDateTime.now();
    }
}
