package com.nhnacademy.minidooraytaskapi.project.entity;

import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_status_id")
    private ProjectStatus projectStatus;
    private String name;
    private String description;
}
