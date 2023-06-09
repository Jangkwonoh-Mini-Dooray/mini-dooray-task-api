package com.nhnacademy.minidooraytaskapi.milestone.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MilestoneDto {
    private Long milestoneId;
    private String name;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private String status;
}
