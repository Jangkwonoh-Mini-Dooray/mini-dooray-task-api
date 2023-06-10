package com.nhnacademy.minidooraytaskapi.comment.controller;

import com.nhnacademy.minidooraytaskapi.comment.dto.CommentIdDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.RequestCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.dto.ResponseCommentDto;
import com.nhnacademy.minidooraytaskapi.comment.service.CommentService;
import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.util.ValidateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class CommentController implements ValidateParam {
    private final CommentService commentService;

    @GetMapping("/{task-id}/comments")
    public ResponseEntity<List<ResponseCommentDto>> getComments(@PathVariable("task-id") Long id) {
        List<ResponseCommentDto> comments = commentService.getComment(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{task-id}/comments")
    public ResponseEntity<CommentIdDto> postComment(@RequestBody @Valid RequestCommentDto requestCommentDto, BindingResult bindingResult, @PathVariable("task-id") Long id) {
        validate(bindingResult);
        CommentIdDto commentIdDto = commentService.postComment(requestCommentDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentIdDto);
    }
    @PutMapping("/comments/{comment-id}")
    public ResponseEntity<CommentIdDto> putComment(@RequestBody @Valid RequestCommentDto requestCommentDto, BindingResult bindingResult, @PathVariable("comment-id") Long id) {
        validate(bindingResult);
        CommentIdDto commentIdDto = commentService.putComment(requestCommentDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentIdDto);
    }
    @DeleteMapping ("/comments/{comment-id}")
    public ResponseEntity<Response> deleteComment(@PathVariable("comment-id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(new Response("OK"));
    }
}
