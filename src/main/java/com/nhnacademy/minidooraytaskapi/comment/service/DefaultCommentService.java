package com.nhnacademy.minidooraytaskapi.comment.service;

import com.nhnacademy.minidooraytaskapi.comment.dto.CommentIdDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.RequestCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.entity.Comment;
import com.nhnacademy.minidooraytaskapi.comment.repository.CommentRepository;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundCommentException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundTaskException;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultCommentService implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCommentDto> getComment(Long taskId) {
        return commentRepository.getCommentByTaskId(taskId);
    }

    @Override
    public CommentIdDto postComment(RequestCommentDto requestCommentDto, Long taskId) {
        Comment comment = new Comment();
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundTaskException(taskId));
        comment.save(task, requestCommentDto.getCommentWriterMemberId(), requestCommentDto.getComment(), LocalDateTime.now());

        Comment save = commentRepository.save(comment);
        CommentIdDto commentIdDto = new CommentIdDto();
        commentIdDto.setCommentId(save.getCommentId());

        return commentIdDto;
    }

    @Override
    public CommentIdDto putComment(RequestCommentDto requestCommentDto, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentException(commentId));
        comment.update(requestCommentDto.getComment(), LocalDateTime.now());

        Comment save = commentRepository.save(comment);
        CommentIdDto commentIdDto = new CommentIdDto();
        commentIdDto.setCommentId(save.getCommentId());

        return commentIdDto;
    }

    @Override
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new NotFoundCommentException(commentId);
        }
        commentRepository.deleteById(commentId);
    }
}
