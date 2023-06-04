package com.nhnacademy.minidooraytaskapi.getTag.entity;

import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "get_tag")
public class GetTag {
    @EmbeddedId
    private Pk pk;
    @Embeddable
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "tag_id")
        private Long tagId;
        @Column(name = "task_id")
        private Long taskId;
    }

    @MapsId("tagId")
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
    @MapsId("taskId")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
