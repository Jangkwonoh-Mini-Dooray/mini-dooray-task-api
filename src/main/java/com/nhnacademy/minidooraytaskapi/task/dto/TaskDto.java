package com.nhnacademy.minidooraytaskapi.task.dto;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long taskId;
    private String taskWriterMemberId;
    private Milestone milestone;
    private String title;
}
