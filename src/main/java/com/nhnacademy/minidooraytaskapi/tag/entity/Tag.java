package com.nhnacademy.minidooraytaskapi.tag.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class Tag {
    @EmbeddedId
    private Pk pk;

    private String name;

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "tag_id")
        private Long tagId;
        @Column(name = "project_id")
        private Long projectId;
    }
}
