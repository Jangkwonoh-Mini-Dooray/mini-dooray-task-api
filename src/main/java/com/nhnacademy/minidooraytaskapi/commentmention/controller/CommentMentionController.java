package com.nhnacademy.minidooraytaskapi.commentmention.controller;

import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionRequestDto;
import com.nhnacademy.minidooraytaskapi.commentmention.dto.CommentMentionResponseDto;
import com.nhnacademy.minidooraytaskapi.commentmention.service.CommentMentionService;
import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.util.ValidateParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mentions/{comment-id}")
@RequiredArgsConstructor
@Slf4j
public class CommentMentionController implements ValidateParam {
    private final CommentMentionService commentMentionService;

    @GetMapping
    public ResponseEntity<List<CommentMentionResponseDto>> getCommentMentions(@PathVariable("comment-id") Long commentId) {
        List<CommentMentionResponseDto> result = commentMentionService.getCommentMentions(commentId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<Response> createCommentMention(@PathVariable("comment-id") Long commentId,
                                                         @RequestBody @Valid CommentMentionRequestDto commentMentionRequestDto,
                                                         BindingResult bindingResult) {
        validate(bindingResult);
        commentMentionService.putCommentMention(commentId, commentMentionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("OK"));
    }

    @PutMapping
    public ResponseEntity<Response> modifyCommentMention(@PathVariable("comment-id") Long commentId,
                                                         @RequestBody @Valid CommentMentionRequestDto commentMentionRequestDto,
                                                         BindingResult bindingResult) {
        validate(bindingResult);
        commentMentionService.putCommentMention(commentId, commentMentionRequestDto);
        return ResponseEntity.ok().body(new Response("OK"));
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteCommentMention(@PathVariable("comment-id") Long commentId,
                                                         @RequestBody @Valid CommentMentionRequestDto commentMentionRequestDto,
                                                         BindingResult bindingResult) {
        validate(bindingResult);
        commentMentionService.deleteCommentMention(commentId, commentMentionRequestDto);
        return ResponseEntity.ok().body(new Response("OK"));
    }
}
