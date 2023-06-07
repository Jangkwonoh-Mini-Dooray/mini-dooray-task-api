package com.nhnacademy.minidooraytaskapi.tag.repository;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Tag : Repository 테스트")
class TagRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    TagRepository tagRepository;

    @Test
    @DisplayName("프로젝트에 해당하는 테크 가져오는 Repository")
    void getTagByProjectId() {
        Tag tag = new Tag();
        Tag tag2 = new Tag();

        Project project = new Project();
        project.setName("ggg");
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setName("test");
        project.setProjectStatus(projectStatus);
        Milestone milestone = new Milestone();
        milestone.setProject(project);
        milestone.setName("test");

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);
        testEntityManager.persist(milestone);

        tag.setName("test1");
        tag.setProject(project);
        tag2.setProject(project);
        tag2.setName("test2");

        testEntityManager.persist(tag);
        testEntityManager.persist(tag2);

        List<TagDto> allTag = tagRepository.getTagByProjectId(project.getProjectId());

        Assertions.assertThat(allTag).hasSize(2);
        Assertions.assertThat(allTag.get(0).getTagId()).isEqualTo(tag.getTagId());

    }
}