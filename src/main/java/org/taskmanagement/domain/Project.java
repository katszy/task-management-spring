package org.taskmanagement.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @JsonManagedReference
    @ManyToMany(mappedBy = "projects")
    private List<User> users;
}
