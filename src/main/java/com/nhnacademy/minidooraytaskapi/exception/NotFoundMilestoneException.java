package com.nhnacademy.minidooraytaskapi.exception;

public class NotFoundMilestoneException extends RuntimeException {
    public NotFoundMilestoneException(Long milestoneId) {
        super("There is no milestone id number " + milestoneId);
    }
}
