package com.nhnacademy.minidooraytaskapi.task.entity;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "task")
@NoArgsConstructor
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
    public void save(TaskRequestDto taskRequestDto) {
        this.title = taskRequestDto.getTitle();
        this.content = taskRequestDto.getContent();
        this.taskWriterMemberId = taskRequestDto.getTaskWriterMemberId();
    }
    public void setProject(Project project) {
        this.project = project;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }
}