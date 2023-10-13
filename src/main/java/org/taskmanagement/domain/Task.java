package org.taskmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private LocalDate dueDate;
    private String status;
    private Integer priority;
    private User assignedUser;
    private Project project;
}
