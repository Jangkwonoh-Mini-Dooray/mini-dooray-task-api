package com.nhnacademy.minidooraytaskapi.milestone.dto;

import java.time.LocalDate;

public interface MilestoneDto {
    Long getMilestoneId();
    String getName();
    LocalDate getStartPeriod();
    LocalDate getEndPeriod();
    String getStatus();
}
