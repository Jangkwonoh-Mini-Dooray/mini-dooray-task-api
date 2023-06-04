package com.nhnacademy.minidooraytaskapi.getProject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "get_project")
public class GetProject {
    @EmbeddedId
    private Pk pk;

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "target_member_id")
        private Long targetMemberId;
        @Column(name = "project_id")
        private Long projectId;
    }
}
