package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundProjectMemberException extends RuntimeException {
    public NotFoundProjectMemberException() {
        super("There is no project member.");
    }
}
