package com.cns.assignment.service;

import com.cns.assignment.model.ProjectEntity;

public interface ProjectManagementService {
    ProjectEntity createProject(ProjectEntity projectEntity, Long uid);
    Boolean removeProject(Long id);
    Boolean updateProjectById(ProjectEntity updatedProjectEntity);
    Iterable<ProjectEntity> getProjects(int page, int limit);
    Boolean assignOneUserToProject(Long id);
}
