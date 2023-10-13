package org.taskmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Task {
    private int id;
    private String title;
    private LocalDate dueDate;
    private String status;
    private Integer priority;
    private List<User> assignedUser;
    private Comment comment;
   // private Project project;

    public void createAssignedList(User user){
        assignedUser = List.of(user);
    }
}
