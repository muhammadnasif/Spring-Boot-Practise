package com.cns.assignment.service;

import com.cns.assignment.model.ProjectEntity;

public interface ProjectManagementService {
    String getMessage();
    ProjectEntity createProject(ProjectEntity projectEntity);
    Boolean removeProject(Long id);
}
