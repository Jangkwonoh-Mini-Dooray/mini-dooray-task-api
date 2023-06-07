package com.nhnacademy.minidooraytaskapi.tag.controller;

import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    @GetMapping("{project-id}/tags")
    public ResponseEntity<List<TagDto>> getTags(@PathVariable("project-id") Long projectId) {
        List<TagDto> tags = tagService.getTags(projectId);
        return ResponseEntity.ok(tags);
    }
}
