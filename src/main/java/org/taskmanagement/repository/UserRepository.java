package org.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    Optional<User> findByUsername(String username);
}
