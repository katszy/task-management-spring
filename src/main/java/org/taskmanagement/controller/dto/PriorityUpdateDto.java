package org.taskmanagement.controller.dto;

public class PriorityUpdateDto {
    private Integer newPriority;

    public PriorityUpdateDto() {}

    public Integer getNewPriority() {
        return newPriority;
    }

    public void setNewPriority(Integer newPriority) {
        this.newPriority = newPriority;
    }
}
