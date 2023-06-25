package com.cns.assignment.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("The project with id " + id + " is not found.");
    }
}
