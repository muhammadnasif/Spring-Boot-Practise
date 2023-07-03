package com.cns.assignment.controller;


import com.cns.assignment.dto.UsersIdDTO;
import com.cns.assignment.model.ProjectEntity;
import com.cns.assignment.model.UserEntity;
import com.cns.assignment.service.ProjectManagementService;
import com.cns.assignment.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("api/project")
@CrossOrigin(origins = "http://localhost:8081")
public class ProjectController {

    private final ProjectManagementService projectManagementService;
    private final ReportService reportService;

    public ProjectController(ProjectManagementService projectManagementService, ReportService reportService) {
        this.projectManagementService = projectManagementService;
        this.reportService = reportService;
    }

    @GetMapping("/")
    public Iterable<ProjectEntity> showProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return this.projectManagementService.getProjects(page, limit);
    }

    @GetMapping("/{id}")
    public ProjectEntity getProjectById(@PathVariable Long id) {
        return this.projectManagementService.getProjectById(id);
    }

    @PostMapping("/")
    ProjectEntity createProject(@Valid @RequestBody ProjectEntity projectEntity, @RequestParam("uid") Long uid) {
        // saving a new project returns that entity's object that is stored in this "newProject"
        return this.projectManagementService.createProject(projectEntity, uid);
    }

    @PutMapping("/")
    ResponseEntity<String> updateProject(@Valid @RequestBody ProjectEntity project) {
        if (this.projectManagementService.updateProjectById(project)) return ResponseEntity.ok("Updated Successfully");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occurred in the server");
    }

    @DeleteMapping("/{id}/delete/")
    ResponseEntity<String> deleteProject(@PathVariable Long id) {
        if (this.projectManagementService.removeProject(id)) return ResponseEntity.ok("Project deleted successfully");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found or deletion failed");
    }

    @PostMapping("/{id}/assign/")
    String assignUsersToProject(@PathVariable Long id, @RequestBody UsersIdDTO usersIdDTO) {
        if (this.projectManagementService.assignUsersToProject(id, usersIdDTO)) return "success";
        return "failed";
    }

    @GetMapping("/{id}/assigned-users/")
    public Iterable<UserEntity> getAssignedUsersByProjectId(@PathVariable Long id) {
        return this.projectManagementService.assignedUserOfProjectById(id);
    }

    @GetMapping("/{id}/generate-report/")
    public String generateJasperReport(@PathVariable Long id) throws JRException, IOException {
        return this.reportService.exportReport(id);
    }

}
