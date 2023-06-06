package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundProjectStatusException extends RuntimeException {
    public NotFoundProjectStatusException() {
        super("There is no project status id");
    }
}
