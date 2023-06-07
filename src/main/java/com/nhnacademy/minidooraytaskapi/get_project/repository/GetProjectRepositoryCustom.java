package com.nhnacademy.minidooraytaskapi.get_project.repository;

import com.nhnacademy.minidooraytaskapi.get_project.dto.TargetMemberIdDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GetProjectRepositoryCustom {
    List<TargetMemberIdDto> findTargetMembers(Long projectId);
}
