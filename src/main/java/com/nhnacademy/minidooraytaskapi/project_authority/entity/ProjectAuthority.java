package com.nhnacademy.minidooraytaskapi.project_authority.entity;

import javax.persistence.*;

@Entity
@Table(name = "project_authority")
public class ProjectAuthority {
    @Id
    @Column(name = "project_authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectAuthorityId;

    @Column(name = "name")
    private String name;
}