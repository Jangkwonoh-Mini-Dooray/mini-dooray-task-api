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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        project.setProjectId(1L);
        project.setName("ggg");
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setProjectStatusId(3);
        projectStatus.setName("활성");
        project.setProjectStatus(projectStatus);
        Milestone milestone = new Milestone();
        milestone.setMilestoneId(1L);
        milestone.setProject(project);
        milestone.setName("test");

        testEntityManager.merge(projectStatus);
        testEntityManager.merge(project);
        testEntityManager.merge(milestone);

        task.setTaskId(1L);
        task.setTitle("test");
        task.setTaskWriterMemberId("naht94");
        task.setProject(project);
        task.setMilestone(milestone);
        task2.setTaskId(2L);
        task2.setTitle("test");
        task2.setTaskWriterMemberId("naht94");
        task2.setProject(project);
        task2.setMilestone(milestone);

        testEntityManager.merge(task);
        testEntityManager.merge(task2);


        List<TaskDto> allTask = taskRepository.getAllByProjectProjectId(1L);

        assertThat(allTask).isNotEmpty().hasSize(2);
        assertThat(allTask.get(0).getTaskId()).isEqualTo(1L);
    }
}