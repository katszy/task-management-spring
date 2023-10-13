package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MockUserRepository implements UserRepository {

    static List<User> USERS = List.of(
            new User(0, "Kata", "kat@example.com", "password123", "admin"),
            new User(1, "Katrin", "katrin@example.com", "securePass", "user"),
            new User(2, "Alexa", "alexa@example.com", "p@ssw0rd", "user"),
            new User(3, "Eniko", "eni@example.com", "adminPass123", "user")
    );


    @Override
    public List<User>  findAll() {
        return USERS;
    }

    public User findByUsername(String username) {
        Optional<User> userOptional = USERS.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();

        return userOptional.orElse(null);
    }

}
