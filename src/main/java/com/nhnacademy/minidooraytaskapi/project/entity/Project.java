package com.nhnacademy.minidooraytaskapi.project.entity;

import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public Project(ProjectStatus projectStatus, ProjectRequestDto projectRequestDto) {
        this.projectStatus = projectStatus;
        this.name = projectRequestDto.getName();
        this.description = projectRequestDto.getDescription();
    }

    public void modify(ProjectStatus projectStatus, ProjectRequestDto projectRequestDto) {
        this.projectStatus = projectStatus;
        this.name = projectRequestDto.getName();
        this.description = projectRequestDto.getDescription();
    }
}
