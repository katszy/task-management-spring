package org.taskmanagement.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Long id;
    private String title;
    private List<Task> tasks;
    private List<User> assignedUsers;
}
