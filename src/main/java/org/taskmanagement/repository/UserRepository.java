package org.taskmanagement.repository;

import org.taskmanagement.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findByUsername(String username);
}
