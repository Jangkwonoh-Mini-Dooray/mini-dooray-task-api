package com.nhnacademy.minidooraytaskapi.tag.entity;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private String name;
}
