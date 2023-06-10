package com.nhnacademy.minidooraytaskapi.milestone.entity;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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

    public Milestone(String name, LocalDate startPeriod, LocalDate endPeriod, String status, Project project) {
        this.name = name;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.status = status;
        this.project = project;
    }

    public void update(String name, LocalDate startPeriod, LocalDate endPeriod, String status) {
        this.name = name;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.status = status;
    }
}
