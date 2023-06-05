package com.nhnacademy.minidooraytaskapi.task.dto;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {
    private Long taskId;
    private String taskWriterMemberId;
    private Milestone milestone;
    private String title;
    public TaskDto(Long taskId, String taskWriterMemberId, Milestone milestone, String title) {
        this.taskId = taskId;
        this.taskWriterMemberId = taskWriterMemberId;
        this.milestone = milestone;
        this.title = title;
    }
}
