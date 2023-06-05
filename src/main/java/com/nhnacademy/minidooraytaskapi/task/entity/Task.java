package com.nhnacademy.minidooraytaskapi.task.entity;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "task_writer_member_id")
    private String taskWriterMemberId;
}