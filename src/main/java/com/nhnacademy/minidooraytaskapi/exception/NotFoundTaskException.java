package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundTaskException extends RuntimeException {
    public NotFoundTaskException(Long taskId) {
        super("There is no task id number " + taskId);
    }
}
