package com.nhnacademy.minidooraytaskapi.task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PostTaskDto {
    @NotNull(message = "업무 아이디가 존재하지 않습니다.")
    private Long taskId;
    private String taskWriterMemberId;
    @Nullable
    private Long milestoneId;
    private String title;
    private String content;
}
