package com.nhnacademy.minidooraytaskapi.tag.repository;

import com.nhnacademy.minidooraytaskapi.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
