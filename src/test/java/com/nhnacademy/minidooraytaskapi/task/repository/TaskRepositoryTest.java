package com.nhnacademy.minidooraytaskapi.task.repository;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

        testEntityManager.merge(project);

        task.setTaskId(1L);
        task.setProject(project);
        task2.setTaskId(2L);
        task2.setProject(project);

        testEntityManager.merge(task);
        testEntityManager.merge(task2);


        List<Task> allTask = taskRepository.getAllByProject_ProjectId(1L);

        assertThat(allTask).isNotEmpty();
        assertThat(allTask).hasSize(2);
        assertThat(allTask.get(0).getTaskId()).isEqualTo(1L);
    }
}