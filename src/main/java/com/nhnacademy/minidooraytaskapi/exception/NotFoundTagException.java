package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundTagException extends RuntimeException {
    public NotFoundTagException(Long id) {
        super("There is no tag id number " + id);
    }
}
