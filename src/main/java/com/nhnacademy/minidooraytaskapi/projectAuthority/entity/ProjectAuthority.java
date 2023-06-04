package com.nhnacademy.minidooraytaskapi.projectAuthority.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_authority")
public class ProjectAuthority {
    @Id
    private Long projectAuthorityId;
    private String name;
}
