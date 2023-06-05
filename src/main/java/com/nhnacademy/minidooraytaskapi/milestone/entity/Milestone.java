package com.nhnacademy.minidooraytaskapi.milestone.entity;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "milestone")
public class Milestone {
    @Id
    @Column(name = "milestone_id")
    private Long milestoneId;
    private String name;
    @Column(name = "start_period")
    private LocalDate startPeriod;
    @Column(name = "end_period")
    private LocalDate endPeriod;
    private String status;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
