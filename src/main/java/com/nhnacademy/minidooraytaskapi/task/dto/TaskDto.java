package com.nhnacademy.minidooraytaskapi.task.dto;

public interface TaskDto {
    Long getTaskId();
    Long getTaskWriterId();
    Long getMilestoneId();
    String getTitle();
}
