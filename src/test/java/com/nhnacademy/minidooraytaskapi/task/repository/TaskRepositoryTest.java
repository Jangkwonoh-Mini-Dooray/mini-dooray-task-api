package com.nhnacademy.minidooraytaskapi.task.repository;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.project.dto.ProjectRequestDto;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectstatus.entity.ProjectStatus;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskRequestDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

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
        ProjectRequestDto projectRequestDto = new ProjectRequestDto("test", "ggg", "소녀시대");
        Project project = new Project(projectStatus, projectRequestDto);
        Milestone milestone = new Milestone();

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);
        testEntityManager.persist(milestone);

        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L, "세번째 업무", "테스트 업무");
        task.save(taskRequestDto);
        task.setProject(project);
        task.setMilestone(milestone);
        TaskRequestDto taskRequestDto2 = new TaskRequestDto("naht94", 1L, "세번째 업무", "테스트 업무");

        task2.save(taskRequestDto2);
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
        ProjectRequestDto projectRequestDto = new ProjectRequestDto("test", "ggg", "소녀시대");
        Project project = new Project(projectStatus, projectRequestDto);
        Milestone milestone = new Milestone();

        testEntityManager.persist(projectStatus);
        testEntityManager.persist(project);
        testEntityManager.persist(milestone);

        TaskRequestDto taskRequestDto = new TaskRequestDto("naht94", 1L, "세번째 업무", "테스트 업무");
        task.save(taskRequestDto);
        task.setProject(project);
        task.setMilestone(milestone);
        TaskRequestDto taskRequestDto2 = new TaskRequestDto("naht94", 1L, "네번째 업무", "테스트 업무");
        task2.save(taskRequestDto2);
        task2.setProject(project);
        task2.setMilestone(milestone);


        testEntityManager.persist(task);
        testEntityManager.persist(task2);

        assertThat(taskRepository.getTask(project.getProjectId(), task.getTaskId()).getTaskId()).isEqualTo(task.getTaskId());
    }
}