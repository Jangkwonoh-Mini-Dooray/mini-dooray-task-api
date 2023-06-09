package com.nhnacademy.minidooraytaskapi.projectstatus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_status")
public class ProjectStatus {
    @Id
    @Column(name = "project_status_id")
    private int projectStatusId;

    @Column(name = "name")
    private String name;

    public ProjectStatus(String name) {
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
}
