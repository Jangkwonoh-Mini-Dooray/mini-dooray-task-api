package com.nhnacademy.minidooraytaskapi.task.controller;

import com.nhnacademy.minidooraytaskapi.exception.PostDtoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTaskAdvice {
    @ExceptionHandler(value = PostDtoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String BadRequest(PostDtoException ex) {
        return ex.getMessage();
    }
}
