package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundProjectStatusException extends RuntimeException {
    public NotFoundProjectStatusException(int id) {
        super("There is no project status id number " + id);
    }
}
