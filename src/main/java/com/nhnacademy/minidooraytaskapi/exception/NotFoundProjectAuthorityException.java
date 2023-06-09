package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundProjectAuthorityException extends RuntimeException {
    public NotFoundProjectAuthorityException(int projectAuthorityId) {
        super("There is no project authority id" + projectAuthorityId);
    }
}
