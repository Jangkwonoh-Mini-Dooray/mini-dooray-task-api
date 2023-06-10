package com.nhnacademy.minidooraytaskapi.comment.repository;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

}
