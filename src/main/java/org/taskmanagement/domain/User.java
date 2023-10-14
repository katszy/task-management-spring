package org.taskmanagement.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private ArrayList<Task> tasks;

    public void addTaskToList(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>(List.of(task));
        } else {
            tasks.add(task);
        }
    }

}
