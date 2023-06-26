package com.cns.assignment.repository;

import com.cns.assignment.exception.ProjectNotFoundException;
import com.cns.assignment.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}
