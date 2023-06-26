package com.cns.assignment.service;

import com.cns.assignment.model.UserEntity;
import com.cns.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService{

    private final UserRepository userRepository;

    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createNewUser(UserEntity user) {
        return this.userRepository.save(user);
    }

}
