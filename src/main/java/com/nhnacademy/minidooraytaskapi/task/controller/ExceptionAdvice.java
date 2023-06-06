package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.exception.PostTaskDtoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = PostTaskDtoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String BadRequest(PostTaskDtoException ex) {
        return ex.getMessage();
    }
}
