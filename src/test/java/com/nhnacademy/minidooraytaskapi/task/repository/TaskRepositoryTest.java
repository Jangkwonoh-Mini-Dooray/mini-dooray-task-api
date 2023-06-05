package com.nhnacademy.minidooraytaskapi.task.repository;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class TaskRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    TaskRepository taskRepository;

    @Test
    void getAllByProject_ProjectId() {
        Task task = new Task();
        Task task2 = new Task();

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

        task.setTitle("test");
        task.setTaskWriterMemberId("naht94");
        task.setProject(project);
        task.setMilestone(milestone);
        task2.setTitle("test");
        task2.setTaskWriterMemberId("naht94");
        task2.setProject(project);
        task2.setMilestone(milestone);

        testEntityManager.persist(task);
        testEntityManager.persist(task2);


        List<TaskDto> allTask = taskRepository.getAllByProjectProjectId(project.getProjectId());

        assertThat(allTask).isNotEmpty().hasSize(2);
        assertThat(allTask.get(0).getTaskId()).isEqualTo(task.getTaskId());
    }
}