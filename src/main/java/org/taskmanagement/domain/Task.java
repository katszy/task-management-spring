package org.taskmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Task {
    private int id;
    private String title;
    private LocalDate dueDate;
    private String status;
    private Integer priority;
    private ArrayList<Comment> comments;

    public void addComment(Comment comment) {
        if (this.comments == null) {
            this.comments = new ArrayList<>();
        }
        this.comments.add(comment);
    }

}
