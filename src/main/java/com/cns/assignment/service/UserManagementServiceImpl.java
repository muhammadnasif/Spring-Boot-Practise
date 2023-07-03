package com.cns.assignment.service;

import com.cns.assignment.model.UserEntity;
import com.cns.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserManagementServiceImpl implements UserManagementService{

    private final UserRepository userRepository;

    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createNewUser(UserEntity user) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setCreatedAt(currentDateTime);

        return this.userRepository.save(user);
    }

    @Override
    public Iterable<UserEntity> getUsers(int page, int limit) {
        return this.userRepository.findAll();
    }


}
