package com.nhnacademy.minidooraytaskapi.project_authority.entity;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Table(name = "project_authority")
public class ProjectAuthority {
    @Id
    @Column(name = "project_authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectAuthorityId;

    @Column(name = "name")
    private String name;
}
