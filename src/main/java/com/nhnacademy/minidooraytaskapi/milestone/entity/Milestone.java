package com.nhnacademy.minidooraytaskapi.milestone.entity;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "milestone")
public class Milestone {
    @EmbeddedId
    private Pk pk;
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "milestone_id")
        private Long milestoneId;
        @Column(name = "project_id")
        private Long projectId;
    }
    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private String name;
    @Column(name = "start_period")
    private LocalDate startPeriod;
    @Column(name = "end_period")
    private LocalDate endPeriod;
    private String status;

}
