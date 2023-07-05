package com.cns.assignment.service;

import com.cns.assignment.dto.UsersIdDTO;
import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.model.UserEntity;

import java.util.List;

public interface ProjectManagementService {
    ProjectEntity createProject(ProjectEntity projectEntity, Long uid);
    ProjectEntity updateProject(ProjectEntity projectEntity);
    Boolean removeProject(Long id);
    Boolean updateProjectById(ProjectEntity updatedProjectEntity);
    Iterable<ProjectEntity> getProjects(int page, int limit);
    Boolean assignUsersToProject(Long id, UsersIdDTO usersIdDTO);
    Iterable<UserEntity> assignedUserOfProjectById(Long id);
    ProjectEntity getProjectById(Long id);
    List<ProjectEntity> getProjectsByOwnerId(Long id);

}
