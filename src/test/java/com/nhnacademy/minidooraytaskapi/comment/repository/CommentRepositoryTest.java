package com.nhnacademy.minidooraytaskapi.comment.repository;

import com.nhnacademy.minidooraytaskapi.comment.dto.RequestCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskRequestDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Comment : Repository 테스트")
class CommentRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("업무에 해당하는 댓글 가져오기")
    void getCommentByTaskId() {
        Comment comment = new Comment();
        Comment comment2 = new Comment();
        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L,"test","content");
        Task task = new Task();
        ProjectStatus projectStatus = new ProjectStatus();
        ProjectRequestDto projectRequestDto = new ProjectRequestDto(projectStatus.getName(), "ggg", "소녀시대");

        Project project = new Project(projectStatus, projectRequestDto);
        Milestone milestone = new Milestone();

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);
        testEntityManager.persist(milestone);

        task.save(taskRequestDto);
        task.setProject(project);
        task.setMilestone(milestone);

        testEntityManager.persist(task);
        RequestCommentDto naht94 = new RequestCommentDto("naht94", "할게 너무 많다아아악!");
        RequestCommentDto nami = new RequestCommentDto("nami", "입닫고 할거 하쇼");
        comment.save(naht94, task);
        comment2.save(nami, task);

        testEntityManager.persist(comment);
        testEntityManager.persist(comment2);

        List<ResponseCommentDto> commentByTaskId = commentRepository.getCommentByTaskId(task.getTaskId());

        Assertions.assertThat(commentByTaskId).hasSize(2);
        Assertions.assertThat(commentByTaskId.get(0).getCommentWriterMemberId()).isEqualTo("naht94");
    }
}