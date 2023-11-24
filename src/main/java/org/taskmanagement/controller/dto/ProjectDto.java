package org.taskmanagement.controller.dto;

import org.taskmanagement.domain.Project;

public class ProjectDto {
    private String title;
    // Other fields that are needed for project creation can be added here

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Optionally, you can add a method to convert DTO to entity
    public Project toEntity() {
        Project project = new Project();
        project.setTitle(this.title);
        // TODO
        return project;
    }
}
