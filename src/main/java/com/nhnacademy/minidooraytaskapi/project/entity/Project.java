package com.nhnacademy.minidooraytaskapi.project.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "project_status_id")
    private int projectStatusId;
    private String name;
    private String description;
}
