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
    @EmbeddedId
    private Pk pk;

    private String name;

    @Getter
    @Setter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "tag_id")
        private Long tagId;
        @Column(name = "project_id")
        private Long projectId;
    }
    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;
}
