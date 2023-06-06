package com.nhnacademy.minidooraytaskapi.exception;

import java.util.Arrays;

public class PostTaskDtoException extends RuntimeException {
    public PostTaskDtoException(String... error) {
        super(Arrays.toString(error));
    }
}
