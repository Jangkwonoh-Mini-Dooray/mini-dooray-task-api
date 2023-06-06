package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundProjectException extends RuntimeException {
    public NotFoundProjectException(Long projectId) {
        super("There is no project id number "+ projectId);
    }
}
