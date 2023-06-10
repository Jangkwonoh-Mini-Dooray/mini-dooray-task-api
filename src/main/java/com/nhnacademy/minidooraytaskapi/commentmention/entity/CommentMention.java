package com.nhnacademy.minidooraytaskapi.commentmention.entity;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment_mention")
public class CommentMention {
    @EmbeddedId
    private Pk pk;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "target_member_id")
        private String targetMemberId;

        @Column(name = "comment_id")
        private Long commentId;
    }

    @MapsId("commentId")
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
