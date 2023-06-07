package com.nhnacademy.minidooraytaskapi.exception;

import java.util.List;

public class PostDtoException extends RuntimeException {
    public PostDtoException(List<String> errors) {
        super(errors.toString());
    }
}
