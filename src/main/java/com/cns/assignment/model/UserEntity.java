package com.cns.assignment.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "project_member",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<ProjectEntity> projectEntities = new HashSet<>();

}
