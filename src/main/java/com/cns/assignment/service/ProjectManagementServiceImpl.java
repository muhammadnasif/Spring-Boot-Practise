package com.cns.assignment.service;


import com.cns.assignment.exception.ProjectNotFoundException;
import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.repository.ProjectRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

    private final ProjectRepository projectRepository;

    ProjectManagementServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String getMessage() {
        return "hello";
    }

    public ProjectEntity createProject(ProjectEntity projectEntity) {
        return this.projectRepository.save(projectEntity);
    }

    public Boolean removeProject(Long id) {
        Optional<ProjectEntity> project  = this.projectRepository.findById(id);
        if (project.isPresent()) {
            this.projectRepository.deleteById(id);
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }


}
