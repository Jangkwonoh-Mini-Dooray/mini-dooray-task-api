package com.nhnacademy.minidooraytaskapi.projectmember.entity;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_member")
public class ProjectMember {
    @EmbeddedId
    private Pk pk;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "target_member_id")
        private String targetMemberId;

        @Column(name = "project_id")
        private Long projectId;
    }
    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "project_authority_id")
    private ProjectAuthority projectAuthority;
}
