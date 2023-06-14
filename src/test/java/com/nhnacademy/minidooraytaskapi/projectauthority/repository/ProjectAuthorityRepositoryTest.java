package com.nhnacademy.minidooraytaskapi.projectauthority.repository;

import com.nhnacademy.minidooraytaskapi.project.entity.Project;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.dto.ProjectAuthorityNameDto;
import com.nhnacademy.minidooraytaskapi.projectauthority.entity.ProjectAuthority;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("ProjectAuthority : Repository 테스트")
class ProjectAuthorityRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProjectAuthorityRepository projectAuthorityRepository;
    @Test
    void getProjectAuthorities() {
        ProjectAuthority projectAuthority = new ProjectAuthority();
        projectAuthority.setName("test1");

        ProjectAuthority projectAuthority2 = new ProjectAuthority();
        projectAuthority2.setName("test2");

        testEntityManager.persist(projectAuthority);
        testEntityManager.persist(projectAuthority2);

        List<ProjectAuthorityDto> projectAuthorities = projectAuthorityRepository.getProjectAuthorities();
        Assertions.assertThat(projectAuthorities).hasSize(2);
        Assertions.assertThat(projectAuthorities.get(0).getProjectAuthorityId()).isEqualTo(3);
        Assertions.assertThat(projectAuthorities.get(0).getName()).isEqualTo("test1");
    }

    @Test
    void getProjectAuthority() {
        ProjectAuthority projectAuthority = new ProjectAuthority();
        projectAuthority.setName("test1");

        ProjectAuthority projectAuthority2 = new ProjectAuthority();
        projectAuthority2.setName("test2");

        testEntityManager.persist(projectAuthority);
        testEntityManager.persist(projectAuthority2);

        ProjectAuthorityNameDto result = projectAuthorityRepository.getProjectAuthority(1);
        Assertions.assertThat(result.getName()).isEqualTo("test1");
    }
}