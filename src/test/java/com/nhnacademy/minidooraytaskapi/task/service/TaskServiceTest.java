package com.nhnacademy.minidooraytaskapi.task.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundMilestoneException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundTaskException;
import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import com.nhnacademy.minidooraytaskapi.milestone.repository.MilestoneRepository;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.task.dto.PostTaskDto;
import com.nhnacademy.minidooraytaskapi.task.dto.TaskDto;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Task : Service 테스트")
class TaskServiceTest {
    @Autowired
    TaskService taskService;
    @MockBean
    TaskRepository taskRepository;
    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    MilestoneRepository milestoneRepository;

    @BeforeEach
    void setUp() {
            MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("프로젝트에 존재하는 모든 업무 가져오는 서비스")
    void getAllByProjectId() {
        Task task = new Task();
        Task task2 = new Task();

        Project project = new Project();
        project.setProjectId(1L);

        task.setTaskId(1L);
        task.setProject(project);
        task2.setTaskId(2L);
        task2.setProject(project);


        given(taskRepository.getTasks(anyLong()))
                .willReturn(List.of(new TaskDto(task.getTaskId(), task.getTaskWriterMemberId(), task.getMilestone(), task.getTitle()),
                        new TaskDto(task2.getTaskId(), task2.getTaskWriterMemberId(), task2.getMilestone(), task.getTitle())));

        List<TaskDto> allTask = taskService.getAllByProjectId(project.getProjectId());

        Assertions.assertThat(allTask).isNotEmpty().hasSize(2);
        Assertions.assertThat(allTask.get(0).getTaskId()).isEqualTo(task.getTaskId());
    }

    @Test
    @DisplayName("프로젝트에 존재하는 특정 업무 가져오는 서비스")
    void testGetTaskByTaskIdAndProjectId() {
        Task task = new Task();

        Project project = new Project();
        project.setProjectId(1L);

        task.setTaskId(1L);
        task.setProject(project);

        given(taskRepository.getTask(task.getTaskId(), project.getProjectId()))
                .willReturn(new TaskDto(task.getTaskId(), task.getTaskWriterMemberId(), task.getMilestone(), task.getTitle()));

        assertThat(taskService.getTaskByTaskIdAndProjectId(task.getTaskId(), project.getProjectId()).getTaskId()).isEqualTo(task.getTaskId());
    }

    @Test
    @DisplayName("프로젝트에 업무 수정 및 저장하는 서비스 #실패 1 속하는 프로젝트 없음")
    void saveTask() {
        PostTaskDto postTaskDto = new PostTaskDto();
        Task task = new Task();

        given(projectRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(NotFoundProjectException.class, () -> taskService.saveTask(postTaskDto, 1L, task));
    }

    @Test
    @DisplayName("프로젝트에 업무 수정 및 저장하는 서비스 #실패 2 가져온 마일스톤 아이디가 없는 아이디인 경우")
    void saveTask2() {
        PostTaskDto postTaskDto = new PostTaskDto();
        Task task = new Task();
        Project project = new Project();
        project.setProjectId(1L);

        postTaskDto.setMilestoneId(1L);
        given(projectRepository.findById(anyLong())).willReturn(Optional.of(project));
        given(milestoneRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(NotFoundMilestoneException.class, () -> taskService.saveTask(postTaskDto, 1L, task));
    }

    @Test
    @DisplayName("프로젝트 업무 수정 및 저장하는 서비스 #성공 1 마일스톤 없음")
    void saveTask3() {
        Task task = new Task();
        PostTaskDto postTaskDto = new PostTaskDto();
        Project project = new Project();

        project.setProjectId(1L);

        given(projectRepository.findById(anyLong())).willReturn(Optional.of(project));

        task.setProject(project);

        given(taskRepository.saveAndFlush(any())).willReturn(task);

        assertThat(taskService.saveTask(postTaskDto, 1L, task)).isEqualTo(task.getTaskId());
    }

    @Test
    @DisplayName("프로젝트 업무 수정 및 저장하는 서비스 #성공 2 마일스톤 있음")
    void saveTask4() {
        Task task = new Task();
        PostTaskDto postTaskDto = new PostTaskDto();
        Project project = new Project();
        Milestone milestone = new Milestone();

        project.setProjectId(1L);
        milestone.setMilestoneId(1L);

        given(projectRepository.findById(anyLong())).willReturn(Optional.of(project));
        given(milestoneRepository.findById(anyLong())).willReturn(Optional.of(milestone));

        task.setProject(project);
        task.setMilestone(milestone);

        given(taskRepository.saveAndFlush(any())).willReturn(task);

        assertThat(taskService.saveTask(postTaskDto, 1L, task)).isEqualTo(task.getTaskId());
    }

    @Test
    @DisplayName("프로젝트에 존재하는 특정 업무 엔티티 가져오는 서비스 #실패")
    void putTask() {
        PostTaskDto postTaskDto = new PostTaskDto();
        given(taskRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(NotFoundTaskException.class, () -> taskService.putTask(postTaskDto, 1L, 1L));
    }

    @Test
    @DisplayName("특정 업무 삭제 #실패")
    void deleteTask() {
        given(taskRepository.existsById(anyLong())).willReturn(false);
        assertThrows(NotFoundTaskException.class, () -> taskService.deleteTask(1L));
    }

    @Test
    @DisplayName("특정 업무 삭제")
    void deleteTask2() {
        given(taskRepository.existsById(anyLong())).willReturn(true);
        doNothing().when(taskRepository).deleteById(anyLong());

        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}