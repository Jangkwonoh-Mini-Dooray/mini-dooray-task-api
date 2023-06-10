package com.nhnacademy.minidooraytaskapi.commentmention.repository;

import com.nhnacademy.minidooraytaskapi.commentmention.entity.CommentMention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentMentionRepository extends JpaRepository<CommentMention, CommentMention.Pk>, CommentMentionRepositoryCustom {
}
