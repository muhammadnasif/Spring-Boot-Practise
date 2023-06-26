package com.cns.assignment.service;


import com.cns.assignment.dto.UsersIdDTO;
import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.model.UserEntity;
import com.cns.assignment.repository.ProjectRepository;
import com.cns.assignment.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    ProjectManagementServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ProjectEntity createProject(ProjectEntity projectEntity, Long uid) {

        Optional<UserEntity> owner = this.userRepository.findById(uid);
        if (owner.isPresent()) projectEntity.setOwner(owner.get());

        return this.projectRepository.save(projectEntity);
    }

    public Boolean removeProject(Long id) {
        Optional<ProjectEntity> project = this.projectRepository.findById(id);
        if (project.isPresent()) {
            this.projectRepository.deleteById(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Iterable<ProjectEntity> getProjects(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return this.projectRepository.findAll(pageable);
    }

    public Boolean updateProjectById(ProjectEntity updatedProjectEntity) {

        Long id = updatedProjectEntity.getId();
        Optional<ProjectEntity> projectEntityOptional = this.projectRepository.findById(id);

        if (projectEntityOptional.isPresent()) {
            ProjectEntity projectEntity = projectEntityOptional.get();

            projectEntity.setName(updatedProjectEntity.getName());
            projectEntity.setIntro(updatedProjectEntity.getIntro());
            projectEntity.setStatus(updatedProjectEntity.getStatus());
            projectEntity.setEndDate(updatedProjectEntity.getEndDate());

            this.projectRepository.save(projectEntity);

            return Boolean.TRUE;
        } else {

            return Boolean.FALSE;
        }
    }

    public Boolean assignUsersToProject(Long project_id, UsersIdDTO usersIdDTO) {

        Optional<ProjectEntity> project = this.projectRepository.findById(project_id);

        if (project.isPresent()) {

            List<Long> userIds = usersIdDTO.getUserId();
            for (Long id : userIds) {
                Optional<UserEntity> user = this.userRepository.findById(id);
                user.ifPresent(userEntity -> project.get().getUsers().add(userEntity));
            }

            this.projectRepository.save(project.get());
            return Boolean.TRUE;
        } else return Boolean.TRUE;
    }


    public Iterable<UserEntity> assignedUserOfProjectById(Long id) {
        Optional<ProjectEntity> project = this.projectRepository.findById(id);
        if (project.isPresent()) {

            System.out.println("Here it is");
            System.out.println("Here it is");
            System.out.println("Here it is");
            System.out.println("Here it is");
            System.out.println("Here it is");

            System.out.println(project.get().getClass().getSimpleName());

            System.out.println("Here it is");
            System.out.println("Here it is");
            System.out.println("Here it is");
            System.out.println("Here it is");
            System.out.println("Here it is");

            return project.get().getUsers();
        } else {
            return null;
        }
    }
}
