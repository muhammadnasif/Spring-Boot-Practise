package com.cns.assignment.service;

import com.cns.assignment.model.RoleEntity;
import com.cns.assignment.model.UserEntity;
import com.cns.assignment.repository.RoleRepository;
import com.cns.assignment.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserManagementServiceImpl implements UserManagementService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagementServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createNewUser(UserEntity user) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setCreatedAt(currentDateTime);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public Iterable<UserEntity> getUsers(int page, int limit) {
        return this.userRepository.findAll();
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        UserEntity user = this.userRepository.findByUsername(username);
        RoleEntity role = this.roleRepository.findByName(rolename);

        user.getRoles().add(role);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found in the database");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach( roleEntity ->
                authorities.add(new SimpleGrantedAuthority(roleEntity.getName()))
        );
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
