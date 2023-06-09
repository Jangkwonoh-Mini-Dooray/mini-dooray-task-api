package com.nhnacademy.minidooraytaskapi.tag.controller;

import com.nhnacademy.minidooraytaskapi.response.Response;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagIdDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TaskTagRequestDto;
import com.nhnacademy.minidooraytaskapi.tag.service.TagService;
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
@RequestMapping("/projects")
@RequiredArgsConstructor
public class TagController implements ValidateParam {
    private final TagService tagService;

    @GetMapping("/{project-id}/tags")
    public ResponseEntity<List<TagDto>> getTags(@PathVariable("project-id") Long projectId) {
        List<TagDto> tags = tagService.getTags(projectId);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{project-id}/tags/{task-id}")
    public ResponseEntity<List<TagDto>> getTagsByTags(@PathVariable("project-id") Long projectId, @PathVariable("task-id") Long taskId) {
        List<TagDto> tags = tagService.getTags(projectId, taskId);
        return ResponseEntity.ok(tags);
    }

    @PostMapping("/{project-id}/tags")
    public ResponseEntity<TagIdDto> createTag(@RequestBody @Valid TaskTagRequestDto taskTagRequest, BindingResult bindingResult, @PathVariable("project-id") Long projectId) {
        validate(bindingResult);
        TagIdDto tagIdDto = tagService.postTagTask(taskTagRequest, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagIdDto);
    }

    @PutMapping("/{project-id}/tags/{tag-id}")
    public ResponseEntity<TagIdDto> modifyTag(@RequestBody @Valid TaskTagRequestDto taskTagRequest, BindingResult bindingResult, @PathVariable("project-id") Long projectId, @PathVariable("tag-id") Long tagId) {
        validate(bindingResult);
        TagIdDto tagIdDto = tagService.putTagTask(taskTagRequest, projectId, tagId);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagIdDto);
    }

    @DeleteMapping("/tags/{tag-id}")
    public ResponseEntity<Response> deleteTag(@PathVariable("tag-id") Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok(new Response("OK"));
    }
}