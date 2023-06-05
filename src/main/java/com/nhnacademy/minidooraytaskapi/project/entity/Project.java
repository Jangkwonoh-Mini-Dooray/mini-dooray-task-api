package com.nhnacademy.minidooraytaskapi.project.entity;

import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "project_id")
    private Long projectId;
    @ManyToOne
    @JoinColumn(name = "project_status_id")
    private ProjectStatus projectStatus;
    private String name;
    private String description;
}
