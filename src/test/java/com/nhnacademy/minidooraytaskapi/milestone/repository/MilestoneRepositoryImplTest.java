package com.nhnacademy.minidooraytaskapi.milestone.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DisplayName("Milestone : Repository 테스트")
class MilestoneRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    MilestoneRepository milestoneRepository;

    @Test
    @Order(1)
    @DisplayName("마일스톤 전체 조회")
    void testFindMilestones() {

    }
}