package com.nhnacademy.minidooraytaskapi.commentmention.repository;

import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.entity.CommentMention;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("CommentMention : Repository 테스트")
class CommentMentionRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    CommentMentionRepository commentMentionRepository;

    @Test
    @Order(1)
    @DisplayName("Comment ID 로 댓글 멘션 전체 조회")
    void testGetCommentMentions() {
        Task task = new Task();
        testEntityManager.persist(task);

        Comment comment = new Comment();
        comment.save(task, "test", "test");
        testEntityManager.persist(comment);

        CommentMention commentMention1 = new CommentMention(new CommentMention.Pk("user", comment.getCommentId()), comment);
        CommentMention commentMention2 = new CommentMention(new CommentMention.Pk("user2", comment.getCommentId()), comment);

        testEntityManager.persist(commentMention1);
        testEntityManager.persist(commentMention2);

        List<CommentMentionResponseDto> actual = commentMentionRepository.getCommentMentions(comment.getCommentId());
        assertThat(actual.size()).isEqualTo(2);
    }
}