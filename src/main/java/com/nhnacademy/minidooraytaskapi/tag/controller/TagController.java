package com.nhnacademy.minidooraytaskapi.tag.controller;

import com.nhnacademy.minidooraytaskapi.exception.PostDtoException;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagIdDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagRequestDto;
import com.nhnacademy.minidooraytaskapi.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class TagController {
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
    public ResponseEntity<TagIdDto> createTags(@RequestBody @Valid TagRequestDto tagRequestDto, BindingResult bindingResult, @PathVariable("project-id") Long projectId) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : errors) {
                errorMessages.add(error.getDefaultMessage());
            }
            throw new PostDtoException(errorMessages);
        }
        TagIdDto tagIdDto = tagService.postTag(tagRequestDto, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagIdDto);
    }
}
