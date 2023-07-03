package com.cns.assignment.repository;

import com.cns.assignment.exception.ProjectNotFoundException;
import com.cns.assignment.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    @Query("SELECT p FROM ProjectEntity p WHERE p.owner.uid = :userId")
    List<ProjectEntity> findProjectsByOwnerId(@Param("userId") Long userId);
}
