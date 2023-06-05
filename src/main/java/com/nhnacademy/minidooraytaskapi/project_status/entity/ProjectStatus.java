package com.nhnacademy.minidooraytaskapi.project_status.entity;

import javax.persistence.*;

@Entity
@Table(name = "project_status")
public class ProjectStatus {
    @Id
    @Column(name = "project_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectStatusId;

    @Column(name = "name")
    private String name;
}
