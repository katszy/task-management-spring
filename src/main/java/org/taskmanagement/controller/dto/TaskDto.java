package org.taskmanagement.controller.dto;

import java.time.LocalDate;

public class TaskDto {
    private String title;
    private LocalDate dueDate;
    private String status;
    private Integer priority;
    private int projectId;
    private int userId;

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public Integer getPriority() {
        return priority;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getUserId() {
        return userId;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
