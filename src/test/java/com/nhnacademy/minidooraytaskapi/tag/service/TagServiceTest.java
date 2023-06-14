package com.nhnacademy.minidooraytaskapi.tag.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundTagException;
import com.nhnacademy.minidooraytaskapi.exception.NotFoundTaskException;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagIdDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TaskTagRequestDto;
import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import com.nhnacademy.minidooraytaskapi.tag.repository.TagRepository;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import com.nhnacademy.minidooraytaskapi.task.repository.TaskRepository;
import com.nhnacademy.minidooraytaskapi.tasktag.entity.TaskTag;
import com.nhnacademy.minidooraytaskapi.tasktag.repository.TaskTagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
@DisplayName("Tag : Service 테스트")
class TagServiceTest {
    @InjectMocks
    DefaultTagService tagService;
    @Mock
    TagRepository tagRepository;
    @Mock
    ProjectRepository projectRepository;
    @Mock
    TaskRepository taskRepository;
    @Mock
    TaskTagRepository taskTagRepository;

    @Test
    @DisplayName("프로젝트에 해당하는 테크 가져오는 Service")
    void getTags() {
        Tag tag = new Tag();
        Tag tag2 = new Tag();

        Project project = new Project();
        ReflectionTestUtils.setField(project, "projectId", 1L);

        tag.setName("test1");
        tag2.setName("test2");
        ReflectionTestUtils.setField(tag, "project", project);
        ReflectionTestUtils.setField(tag2, "project", project);


        when(tagRepository.getTagByProjectId(anyLong()))
                .thenReturn(List.of(new TagDto(tag.getTagId(), tag.getName()),
                        new TagDto(tag2.getTagId(), tag2.getName())));

        List<TagDto> allTag = tagService.getTags(project.getProjectId());

        Assertions.assertThat(allTag).hasSize(2);
        Assertions.assertThat(allTag.get(0).getName()).isEqualTo(tag.getName());
    }

    @Test
    @DisplayName("프로젝트내 업무에 해당하는 태그 가져오는 Service")
    void getTag2() {
        Tag tag = new Tag();
        Tag tag2 = new Tag();

        Project project = new Project();
        ReflectionTestUtils.setField(project, "projectId", 1L);
        Task task = new Task();
        ReflectionTestUtils.setField(task, "taskId", 1L);
        tag.setName("test1");
        tag.setName("test2");

        when(tagRepository.getTagByProjectIdAndTaskId(anyLong(), anyLong()))
                .thenReturn(List.of(new TagDto(tag.getTagId(), tag.getName()),
                        new TagDto(tag2.getTagId(), tag2.getName())));

        List<TagDto> allTag = tagService.getTags(project.getProjectId(), task.getTaskId());

        Assertions.assertThat(allTag).isNotEmpty().hasSize(2);
        Assertions.assertThat(allTag.get(0).getName()).isEqualTo(tag.getName());
    }

    @Test
    @DisplayName("프로젝트내 태그 생성하는 Service #실패 프로젝트 없음")
    void saveTag() {
        TaskTagRequestDto tagRequestDto = new TaskTagRequestDto();

        Mockito.when(projectRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundProjectException.class, () -> tagService.postTagTask(tagRequestDto, 1L));
    }

    @Test
    @DisplayName("프로젝트애 태그 생성하는 Service #실패2 업무 없음")
    void saveTag2() {
        TaskTagRequestDto tagRequestDto = new TaskTagRequestDto();
        tagRequestDto.setTaskId(1L);
        Project project = new Project();
        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.of(project));
        given(taskRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThrows(NotFoundTaskException.class, () -> tagService.postTagTask(tagRequestDto, 1L));
    }

    @Test
    @DisplayName("프로젝트에 태그 생성하는 Service #성공")
    void saveTag3() {
        TaskTagRequestDto tagRequestDto = new TaskTagRequestDto();
        tagRequestDto.setTaskId(1L);
        Project project = new Project();
        Task task = new Task();
        Tag tag = new Tag();
        TaskTag taskTag = new TaskTag();
        ReflectionTestUtils.setField(task, "taskId", 1L);
        ReflectionTestUtils.setField(taskTag, "pk", new TaskTag.Pk(1L, 1L));
        ReflectionTestUtils.setField(tag, "tagId", 1L);
        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.of(project));
        given(tagRepository.saveAndFlush(any()))
                .willReturn(tag);
        given(taskRepository.findById(anyLong()))
                .willReturn(Optional.of(task));
        given(taskTagRepository.save(any()))
                .willReturn(taskTag);

        TagIdDto tagIdDto = tagService.postTagTask(tagRequestDto, anyLong());

        verify(taskTagRepository, times(1)).save(any());
        Assertions.assertThat(tagIdDto.getTagId()).isEqualTo(task.getTaskId());
    }
    @Test
    @DisplayName("프로젝트에 태그 생성하는 Service #성공2 taskId 없음")
    void saveTag4() {
        TaskTagRequestDto tagRequestDto = new TaskTagRequestDto();

        Task task = new Task();
        Tag tag = new Tag();
        TaskTag taskTag = new TaskTag();
        ReflectionTestUtils.setField(task, "taskId", 1L);
        ReflectionTestUtils.setField(taskTag, "pk", new TaskTag.Pk(1L, 1L));
        ReflectionTestUtils.setField(tag, "tagId", 1L);
        given(tagRepository.findById(anyLong()))
                .willReturn(Optional.of(tag));

        given(tagRepository.saveAndFlush(any()))
                .willReturn(tag);

        TagIdDto tagIdDto = tagService.putTagTask(tagRequestDto, 1L, 1L);

        Assertions.assertThat(tagIdDto.getTagId()).isEqualTo(task.getTaskId());
    }

    @Test
    @DisplayName("프로젝트에 태그 수정하는 Service #실패 tagId 없음")
    void modifyTag() {
        TaskTagRequestDto tagRequestDto = new TaskTagRequestDto();
        given(tagRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        assertThrows(NotFoundTagException.class, () -> tagService.putTagTask(tagRequestDto, 1L, 1L));
    }
    @Test
    @DisplayName("프로젝트 태그 삭제 #실패 tagId 없음")
    void deleteTag() {
        given(tagRepository.existsById(anyLong())).willReturn(false);
        assertThrows(NotFoundTagException.class, () -> tagService.deleteTag(1L));
    }

    @Test
    @DisplayName("프로젝트 태그 삭제 #성공")
    void deleteTag2() {
        given(tagRepository.existsById(anyLong())).willReturn(true);
        doNothing().when(tagRepository).deleteById(anyLong());

        tagService.deleteTag(1L);
        verify(tagRepository, times(1)).deleteById(1L);
    }
}