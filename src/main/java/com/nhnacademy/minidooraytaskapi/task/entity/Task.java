package com.nhnacademy.minidooraytaskapi.task.entity;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
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

    public Task() {

    }
    public void save(String title, String content, String taskWriterMemberId) {
        this.title = title;
        this.content = content;
        this.taskWriterMemberId = taskWriterMemberId;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }
}