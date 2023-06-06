package com.nhnacademy.minidooraytaskapi.get_project.entity;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project_authority.entity.ProjectAuthority;
import lombok .EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "get_project")
public class GetProject {
    @EmbeddedId
    private Pk pk;

    @Getter
    @NoArgsConstructor
    @Embeddable
    @EqualsAndHashCode
    public class Pk implements Serializable {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "target_member_id")
        private String targetMemberId;
        @ManyToOne
        @JoinColumn(name = "project_id")
        private Project project;
    }

    @ManyToOne
    @JoinColumn(name = "project_authority_id")
    private ProjectAuthority projectAuthority;
}
