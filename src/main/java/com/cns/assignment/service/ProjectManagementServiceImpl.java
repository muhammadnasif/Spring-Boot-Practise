package com.cns.assignment.service;


import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.repository.ProjectRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

    private final ProjectRepository projectRepository;

    ProjectManagementServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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

    public Iterable<ProjectEntity> getProjects(int page, int limit){
        Pageable pageable = PageRequest.of(page, limit);
        return this.projectRepository.findAll(pageable);
    }

    public Boolean updateProjectById(ProjectEntity updatedProjectEntity) {

        Long id = updatedProjectEntity.getId();
        Optional<ProjectEntity> projectEntityOptional = this.projectRepository.findById(id);

        if(projectEntityOptional.isPresent()) {
            ProjectEntity projectEntity = projectEntityOptional.get();

            projectEntity.setName(updatedProjectEntity.getName());
            projectEntity.setIntro(updatedProjectEntity.getIntro());
            projectEntity.setStatus(updatedProjectEntity.getStatus());
            projectEntity.setEndDate(updatedProjectEntity.getEndDate());

            this.projectRepository.save(projectEntity);

            return Boolean.TRUE;
        }
        else {

            return Boolean.FALSE;
        }
    }

    public Boolean assignOneUserToProject(Long id) {

        return Boolean.TRUE;
    }
}
