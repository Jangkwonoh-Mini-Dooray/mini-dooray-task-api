package com.nhnacademy.minidooraytaskapi.tag.repository;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskRequestDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.tasktag.entity.TaskTag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

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

        Project project = new Project();
        ReflectionTestUtils.setField(project, "name", "ggg");
        ProjectStatus projectStatus = new ProjectStatus("활성");
        ReflectionTestUtils.setField(project, "projectStatus", projectStatus);
        Milestone milestone = new Milestone();

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);
        testEntityManager.persist(milestone);

        Tag tag = new Tag(project, "test1");
        Tag tag2 = new Tag(project, "test2");

        testEntityManager.persist(tag);
        testEntityManager.persist(tag2);

        List<TagDto> allTag = tagRepository.getTagByProjectId(project.getProjectId());

        Assertions.assertThat(allTag).hasSize(2);
        Assertions.assertThat(allTag.get(0).getTagId()).isEqualTo(tag.getTagId());
    }

    @Test
    @DisplayName("해당 프로젝트의 특정 업무의 태그 가져오기")
    void getTask() {


        Project project = new Project();
        ReflectionTestUtils.setField(project, "name", "ggg");
        ProjectStatus projectStatus = new ProjectStatus("활성");
        ReflectionTestUtils.setField(project, "projectStatus", projectStatus);
        Milestone milestone = new Milestone();

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);
        testEntityManager.persist(milestone);

        Task task = new Task();
        Task task2 = new Task();
        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 2L,"title", "content");
        TaskRequestDto taskRequestDto2 = new TaskRequestDto("naht94", 2L,"title2", "content");

        task.save(taskRequestDto);
        task.setProject(project);
        task.setMilestone(milestone);
        task2.save(taskRequestDto2);
        task2.setProject(project);
        task2.setMilestone(milestone);

        testEntityManager.persist(task);
        testEntityManager.persist(task2);

        Tag tag = new Tag(project, "test1");
        Tag tag2 = new Tag(project, "test2");

        testEntityManager.persist(tag);
        testEntityManager.persist(tag2);

        TaskTag getTag = new TaskTag();
        TaskTag getTag2 = new TaskTag();

        getTag.update(new TaskTag.Pk(tag.getTagId(), task.getTaskId()), tag, task);
        getTag2.update(new TaskTag.Pk(tag2.getTagId(), task2.getTaskId()), tag2, task);

        testEntityManager.persist(getTag);
        testEntityManager.persist(getTag2);

        List<TagDto> allTag = tagRepository.getTagByProjectIdAndTaskId(project.getProjectId(), task.getTaskId());

        Assertions.assertThat(allTag).hasSize(2);
        Assertions.assertThat(allTag.get(0).getTagId()).isEqualTo(tag.getTagId());
    }
}