package com.nhnacademy.minidooraytaskapi.exception;

public class DuplicateIntIdException extends RuntimeException {
    public DuplicateIntIdException(int id) {
        super("ID: [" + id + "] already exists.");
    }
}
