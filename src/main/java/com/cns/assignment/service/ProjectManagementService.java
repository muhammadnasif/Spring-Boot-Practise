package com.cns.assignment.service;

import com.cns.assignment.model.ProjectEntity;
import org.springframework.data.domain.Page;

public interface ProjectManagementService {
    ProjectEntity createProject(ProjectEntity projectEntity);
    Boolean removeProject(Long id);
    Boolean updateProjectById(ProjectEntity updatedProjectEntity);
    Iterable<ProjectEntity> getProjects(int page, int limit);
    Boolean assignOneUserToProject(Long id);
}
