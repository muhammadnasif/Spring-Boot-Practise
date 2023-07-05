package com.cns.assignment.controller;


import com.cns.assignment.model.UserEntity;
import com.cns.assignment.service.ReportService;
import com.cns.assignment.service.UserManagementService;
import lombok.Data;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    private final UserManagementService userManagementService;
    private final ReportService reportService;

    public UserController(UserManagementService userManagementService, ReportService reportService) {
        this.reportService = reportService;
        this.userManagementService = userManagementService;
    }


    @PostMapping("/register/")
    UserEntity registerUser(@Valid @RequestBody UserEntity user) {
        return this.userManagementService.createNewUser(user);
    }

    @GetMapping("/")
    ResponseEntity<Iterable<UserEntity>> getUsers(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok().body(this.userManagementService.getUsers(page, limit));
    }

    @GetMapping("/{id}/generate-report/")
    public String generateReport(@PathVariable Long id) throws JRException, IOException {
        return this.reportService.exportReport(id);
    }

    @PostMapping("/add-role/")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        this.userManagementService.addRoleToUser(form.getUsername(), form.getPassword());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String password;
}