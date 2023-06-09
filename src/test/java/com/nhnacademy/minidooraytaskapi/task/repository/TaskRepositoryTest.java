package com.nhnacademy.minidooraytaskapi.task.repository;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project_status.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Task : Repository 테스트")
class TaskRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    TaskRepository taskRepository;

    @Test
    @DisplayName("프로젝트에 해당하는 업무 가져오기")
    void getAllByProjectId() {
        Task task = new Task();
        Task task2 = new Task();

        ProjectStatus projectStatus = new ProjectStatus();
        Project project = new Project(projectStatus, "ggg", "소녀시대");
        Milestone milestone = new Milestone();
        milestone.setProject(project);
        milestone.setName("test");

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);
        testEntityManager.persist(milestone);

        task.save("test","content", "naht94");
        task.setProject(project);
        task.setMilestone(milestone);
        task2.save("test2","content2", "naht94");
        task2.setProject(project);
        task2.setMilestone(milestone);

        testEntityManager.persist(task);
        testEntityManager.persist(task2);


        List<TaskDto> allTask = taskRepository.getTasks(project.getProjectId());

        assertThat(allTask).isNotEmpty().hasSize(2);
        assertThat(allTask.get(0).getTaskId()).isEqualTo(task.getTaskId());
    }

    @Test
    @DisplayName("해당 프로젝트의 특정 업무 가져오기")
    void testGetTaskByTaskIdAndProjectProjectId() {
        Task task = new Task();
        Task task2 = new Task();

        ProjectStatus projectStatus = new ProjectStatus("test");
        Project project = new Project(projectStatus, "ggg", "소녀시대");
        Milestone milestone = new Milestone();
        milestone.setProject(project);
        milestone.setName("test");

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);
        testEntityManager.persist(milestone);

        task.save("test", "content", "naht94");
        task.setProject(project);
        task.setMilestone(milestone);
        task2.save("test2", "content", "naht94");
        task2.setProject(project);
        task2.setMilestone(milestone);


        testEntityManager.persist(task);
        testEntityManager.persist(task2);

        assertThat(taskRepository.getTask(project.getProjectId(), task.getTaskId()).getTaskId()).isEqualTo(task.getTaskId());

    }
}