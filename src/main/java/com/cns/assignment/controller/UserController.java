package com.cns.assignment.controller;


import com.cns.assignment.model.UserEntity;
import com.cns.assignment.service.UserManagementService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserManagementService userManagementService;

    public UserController (UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping("/register/")
    UserEntity registerUser(@Valid @RequestBody UserEntity user) {
        return this.userManagementService.createNewUser(user);
    }
}
