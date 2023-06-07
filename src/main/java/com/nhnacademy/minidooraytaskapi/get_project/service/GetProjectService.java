package com.nhnacademy.minidooraytaskapi.get_project.service;

import com.nhnacademy.minidooraytaskapi.get_project.dto.TargetMemberIdDto;

import java.util.List;

public interface GetProjectService {
    List<TargetMemberIdDto> getTargetMembers(Long projectId);
}
