package com.nhnacademy.minidooraytaskapi.task.entity;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Setter
@Table(name = "task")
public class Task {
    @EmbeddedId
    private Pk pk;
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    public static class Pk implements Serializable {
        @Column(name = "task_id")
        private Long taskId;
        @Column(name = "project_id")
        private Long projectId;
    }

    @ManyToOne
    @JoinColumn(name = "pk.milestone_id")
    private Milestone milestone;
    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "task_writer_member_id")
    private String taskWriterMemberId;
}