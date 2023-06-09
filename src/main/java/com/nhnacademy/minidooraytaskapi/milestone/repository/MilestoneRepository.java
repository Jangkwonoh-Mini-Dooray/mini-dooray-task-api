package com.nhnacademy.minidooraytaskapi.milestone.repository;

import com.nhnacademy.minidooraytaskapi.milestone.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>, MilestoneRepositoryCustom {
}
