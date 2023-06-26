package com.cns.assignment.controller;


import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.service.ProjectManagementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    private final ProjectManagementService projectManagementService;

    public ProjectController(ProjectManagementService projectManagementService) {
        this.projectManagementService = projectManagementService;
    }

    @GetMapping("/")
    public Iterable<ProjectEntity> showProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Iterable<ProjectEntity> pageInfo = this.projectManagementService.getProjects(page, limit);
        return pageInfo;
    }

    @PostMapping("/")
    ProjectEntity createProject(@Valid @RequestBody ProjectEntity projectEntity) {
        // saving a new project returns that entity's object that is stored in this "newProject"
        ProjectEntity newProject = this.projectManagementService.createProject(projectEntity); // need to implement try catch for error handling
        return newProject;
    }

    @PutMapping("/")
    ResponseEntity<String> updateProject(@Valid @RequestBody ProjectEntity project) {
        if(this.projectManagementService.updateProjectById(project)) return ResponseEntity.ok("Updated Successfully");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occurred in the server");
    }

    @DeleteMapping("/{id}/delete/")
    ResponseEntity<String> deleteProject(@PathVariable Long id) {
        if (this.projectManagementService.removeProject(id)) return ResponseEntity.ok("Project deleted successfully");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found or deletion failed");
    }

}
