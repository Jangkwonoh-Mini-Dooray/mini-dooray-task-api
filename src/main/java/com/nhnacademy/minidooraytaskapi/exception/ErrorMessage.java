package com.nhnacademy.minidooraytaskapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {
    private String title;
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorMessage(String title, int status) {
        this.title = title;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
