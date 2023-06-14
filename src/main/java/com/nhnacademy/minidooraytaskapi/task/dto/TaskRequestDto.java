package com.nhnacademy.minidooraytaskapi.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {
    @NotBlank(message = "작성자가 존재하지 않습니다.")
    private String taskWriterMemberId;
    @Nullable
    private Long milestoneId;
    @NotBlank(message = "제목을 기입해주세요.")
    private String title;
    @NotBlank(message = "내용을 기입해주세요.")
    private String content;
}
