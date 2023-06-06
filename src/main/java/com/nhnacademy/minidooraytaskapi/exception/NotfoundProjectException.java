package com.nhnacademy.minidooraytaskapi.exception;

public class NotfoundProjectException extends RuntimeException {
    public NotfoundProjectException(Long projectId) {
        super("There is no project id number "+ projectId);
    }
}
