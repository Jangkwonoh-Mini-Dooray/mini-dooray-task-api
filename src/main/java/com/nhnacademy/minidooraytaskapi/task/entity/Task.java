package com.nhnacademy.minidooraytaskapi.task.entity;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
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
import java.io.Serializable;

@Entity
public class Task {
    @EmbeddedId
    private Pk pk;
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    public static class Pk implements Serializable {
        @Column(name = "task_id")
        private Long taskId;
        @Column(name = "project_id")
        private Long projectId;
    }

    @Column(name = "milestone_id")
    @ManyToOne
    @JoinColumn
    private Milestone milestone;
    @MapsId("projectId")
    @Column
    @JoinColumn
    private Project project;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "task_writer_member_id")
    private String taskWriterMemberId;
}