package com.nhnacademy.minidooraytaskapi.comment_mention.entity;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import javax.persistence.*;

@Entity
@Table(name = "comment_mention")
public class CommentMention {
    @Id
    @Column(name = "target_member_id")
    private Long targetMemberId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
