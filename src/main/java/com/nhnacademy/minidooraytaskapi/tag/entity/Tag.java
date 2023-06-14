package com.nhnacademy.minidooraytaskapi.tag.entity;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_id")
    private Project project;
    private String name;

    public Tag() {
    }
    public Tag(Project project) {
        this.project = project;
    }

    public void setName(String name) {
        this.name = name;
    }
}
