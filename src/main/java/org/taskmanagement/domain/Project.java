package org.taskmanagement.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private int id;
    private String title;
    private ArrayList<Task> tasks;

    public void addTask(Task task) {
        if (this.tasks == null) {
            this.tasks = new ArrayList<>();
        }
        this.tasks.add(task);
    }

    public void removeTask(int taskId) {
        if (tasks != null) {
            for (Task task : tasks) {
                if (task.getId() == taskId) {
                    tasks.remove(task);
                    break;
                }
            }
        }
    }

}
