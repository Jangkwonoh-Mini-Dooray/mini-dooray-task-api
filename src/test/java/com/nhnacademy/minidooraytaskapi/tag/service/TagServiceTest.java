package com.nhnacademy.minidooraytaskapi.tag.service;

import com.nhnacademy.minidooraytaskapi.exception.NotFoundProjectException;
import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagDto;
import com.nhnacademy.minidooraytaskapi.tag.dto.TagRequestDto;
import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import com.nhnacademy.minidooraytaskapi.tag.repository.TagRepository;
import com.nhnacademy.minidooraytaskapi.task.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Tag : Service 테스트")
class TagServiceTest {
    @Autowired
    TagService tagService;
    @MockBean
    TagRepository tagRepository;
    @MockBean
    ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("프로젝트에 해당하는 테크 가져오는 Service")
    void getTags() {
        Tag tag = new Tag();
        Tag tag2 = new Tag();

        Project project = new Project();
        project.setProjectId(1L);

        tag.setName("test1");
        tag2.setName("test2");

        given(tagRepository.getTagByProjectId(anyLong()))
                .willReturn(List.of(new TagDto(tag.getTagId(), tag.getName()),
                        new TagDto(tag2.getTagId(), tag2.getName())));

        List<TagDto> allTag = tagService.getTags(project.getProjectId());

        Assertions.assertThat(allTag).isNotEmpty().hasSize(2);
        Assertions.assertThat(allTag.get(0).getName()).isEqualTo(tag.getName());
    }

    @Test
    @DisplayName("프로젝트내 업무에 해당하는 태그 가져오는 Service")
    void getTag2() {
        Tag tag = new Tag();
        Tag tag2 = new Tag();

        Project project = new Project();
        project.setProjectId(1L);
        Task task = new Task();
        tag.setName("test1");
        tag.setName("test2");

        given(tagRepository.getTagByProjectIdAndTaskId(anyLong(), anyLong()))
                .willReturn(List.of(new TagDto(tag.getTagId(), tag.getName()),
                        new TagDto(tag2.getTagId(), tag2.getName())));

        List<TagDto> allTag = tagService.getTags(project.getProjectId(), task.getTaskId());

        Assertions.assertThat(allTag).isNotEmpty().hasSize(2);
        Assertions.assertThat(allTag.get(0).getName()).isEqualTo(tag.getName());
    }

    @Test
    @DisplayName("프로젝트내 태그 생성하는 Service")
    void postTag() {
        TagRequestDto tagRequestDto = new TagRequestDto();

        given(projectRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThrows(NotFoundProjectException.class, () -> tagService.postTag(tagRequestDto, anyLong()));
    }
}