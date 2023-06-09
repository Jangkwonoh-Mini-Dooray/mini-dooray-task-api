package com.nhnacademy.minidooraytaskapi.tag.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskTagRequestDto {
    private Long taskId;
    private String name;
}
