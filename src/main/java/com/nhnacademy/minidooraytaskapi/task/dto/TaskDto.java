package com.nhnacademy.minidooraytaskapi.task.dto;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;

public interface TaskDto {
    Long getTaskId();
    String getTaskWriterMemberId();
    Milestone getMilestone();
    String getTitle();
}
