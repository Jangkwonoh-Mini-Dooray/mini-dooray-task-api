package com.nhnacademy.minidooraytaskapi.milestone.entity;

import com.nhnacademy.minidooraytaskapi.milestone.dto.MilestoneRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "milestone")
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id")
    private Long milestoneId;
    private String name;
    @Column(name = "start_period")
    private LocalDate startPeriod;
    @Column(name = "end_period")
    private LocalDate endPeriod;
    private String status;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_id")
    private Project project;

    public Milestone(MilestoneRequestDto milestoneRequestDto, Project project) {
        this.name = milestoneRequestDto.getName();
        this.startPeriod = milestoneRequestDto.getStartPeriod();
        this.endPeriod = milestoneRequestDto.getEndPeriod();
        this.status = milestoneRequestDto.getStatus();
        this.project = project;
    }

    public void update(MilestoneRequestDto milestoneRequestDto) {
        this.name = milestoneRequestDto.getName();
        this.startPeriod = milestoneRequestDto.getStartPeriod();
        this.endPeriod = milestoneRequestDto.getEndPeriod();
        this.status = milestoneRequestDto.getStatus();
    }
}
