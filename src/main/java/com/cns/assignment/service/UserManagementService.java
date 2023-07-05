package com.cns.assignment.service;

import com.cns.assignment.model.UserEntity;

public interface UserManagementService {
    UserEntity createNewUser(UserEntity user);
    UserEntity findByUsername(String username, String password);
    Iterable<UserEntity> getUsers(int page, int limit);
}
