package com.nhnacademy.minidooraytaskapi.projectauthority.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
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
