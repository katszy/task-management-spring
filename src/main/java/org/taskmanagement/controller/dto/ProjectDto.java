package org.taskmanagement.controller.dto;

import org.taskmanagement.domain.Project;

public class ProjectDto {
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Project toEntity() {
        Project project = new Project();
        project.setTitle(this.title);
        return project;
    }
}
