package com.nhnacademy.minidooraytaskapi.exception;

import com.nhnacademy.minidooraytaskapi.task.dto.PostTaskDto;

public class PostTaskDtoException extends RuntimeException {
    public PostTaskDtoException(String error) {
        super(error);
    }
}
