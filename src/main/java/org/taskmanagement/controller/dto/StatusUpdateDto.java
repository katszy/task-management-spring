package org.taskmanagement.controller.dto;


public class StatusUpdateDto {
    private String newStatus;

    public StatusUpdateDto() {}

    public String getNewStatus() {
        return newStatus;
    }


    public void setNewPriority(String newStatus) {
        this.newStatus = newStatus;
    }
}

