package com.nhnacademy.minidooraytaskapi.milestone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneRequestDto {
    private String name;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private String status;
}
