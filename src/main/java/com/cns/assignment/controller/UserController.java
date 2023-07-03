package com.cns.assignment.controller;


import com.cns.assignment.model.UserEntity;
import com.cns.assignment.service.ReportService;
import com.cns.assignment.service.UserManagementService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    private final UserManagementService userManagementService;
    private final ReportService reportService;

    public UserController (UserManagementService userManagementService, ReportService reportService) {
        this.reportService = reportService;
        this.userManagementService = userManagementService;
    }


    @PostMapping("/register/")
    UserEntity registerUser(@Valid @RequestBody UserEntity user) {
        return this.userManagementService.createNewUser(user);
    }

    @GetMapping("/")
    Iterable<UserEntity> getUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int limit) {
        return this.userManagementService.getUsers(page, limit);
    }

    @GetMapping("/{id}/generate-report/")
    public String generateReport(@PathVariable Long id) throws JRException, FileNotFoundException {
        return this.reportService.exportReport(id);
    }
}
