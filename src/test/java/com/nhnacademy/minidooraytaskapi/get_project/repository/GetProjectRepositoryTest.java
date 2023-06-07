package com.nhnacademy.minidooraytaskapi.get_project.repository;

import com.nhnacademy.minidooraytaskapi.get_project.entity.GetProject;
import com.nhnacademy.minidooraytaskapi.project.repository.ProjectRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("GetProject : Repository 테스트")
class GetProjectRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    GetProjectRepository getProjectRepository;

    @Test
    @Order(1)
    @DisplayName("Project ID 로 프로젝트 멤버 조회")
    void testFindTargetMembers() {
        GetProject getProject = new GetProject();
        GetProject.Pk pk = new GetProject.Pk();
    }
}