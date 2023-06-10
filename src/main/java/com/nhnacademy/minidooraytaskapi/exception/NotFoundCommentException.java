package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundCommentException extends RuntimeException{
    public NotFoundCommentException(Long id) {
        super("There is no comment id number " + id);
    }
}
