package com.nhnacademy.minidooraytaskapi.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long taskId;
    private Long taskWriterId;
    private Long milestoneId;
    private String title;
}
