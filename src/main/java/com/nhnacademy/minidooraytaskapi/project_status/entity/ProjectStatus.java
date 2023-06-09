package com.nhnacademy.minidooraytaskapi.project_status.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "project_status")
public class ProjectStatus {
    @Id
    @Column(name = "project_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectStatusId;

    @Column(name = "name")
    private String name;

    public ProjectStatus(String name) {
        this.name = name;
    }
}
