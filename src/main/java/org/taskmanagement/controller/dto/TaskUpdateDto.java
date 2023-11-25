package org.taskmanagement.controller.dto;

public class TaskUpdateDto {
    private Integer newPriority;
    private String newStatus;
    public TaskUpdateDto() {}
    public Integer getNewPriority() {
        return newPriority;
    }
    public String getNewStatus() {
        return newStatus;
    }
    public void setNewPriority(Integer newPriority) {
        this.newPriority = newPriority;
    }
}
