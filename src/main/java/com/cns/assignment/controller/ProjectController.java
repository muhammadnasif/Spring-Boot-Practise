package com.cns.assignment.controller;


import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.service.ProjectManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProjectController {

    private final ProjectManagementService projectManagementService;

    public ProjectController(ProjectManagementService projectManagementService) {
        this.projectManagementService = projectManagementService;
    }

    @PostMapping("project/")
    public ProjectEntity createProject(@Valid @RequestBody ProjectEntity projectEntity) {
        // saving a new project returns that entity's object that is stored in this "newProject"
        ProjectEntity newProject = this.projectManagementService.createProject(projectEntity); // need to implement try catch for error handling
        return newProject;
    }

    @PutMapping("project/")
    public ProjectEntity updateProject(@Valid @RequestBody ProjectEntity project) {
        System.out.println("Found it");
//        return this.projectManagementService.insertProject(project);
        return project;
    }



    @DeleteMapping("/project/{id}/delete/")
    ResponseEntity<String> deleteProject(@PathVariable Long id) {
        if (this.projectManagementService.removeProject(id)) return ResponseEntity.ok("Project deleted successfully");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found or deletion failed");
    }

}
