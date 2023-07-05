package com.cns.assignment.service;

import com.cns.assignment.model.UserEntity;

public interface UserManagementService {
    UserEntity createNewUser(UserEntity user);
    Iterable<UserEntity> getUsers(int page, int limit);
    void addRoleToUser(String username, String rolename);

}
