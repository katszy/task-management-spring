package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;

//import static org.taskmanagement.repository.mocks.MockTaskRepository.TASK_1;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MockUserRepository implements UserRepository {

    static List<User> USERS = List.of(
            new User(0, "Kata", "kat@example.com", "password123", "admin",null),
            new User(1, "Katrin", "katrin@example.com", "securePass", "user",null),
            new User(2, "Alexa", "alexa@example.com", "p@ssw0rd", "user",null),
            new User(3, "Eniko", "eni@example.com", "adminPass123", "user",null)
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

    @Override
    public List<Task> viewTaskByUser(String username) {
        Optional<User> userOptional = USERS.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();

        List<Task> userTasks = null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userTasks = user.getTasks();
        } else {
            log.warn("No such user");
        }
        return userTasks;
    }

    @Override
    public void assignTask(User username, Task task) {
        Optional<User> userOptional  = USERS.stream().filter(user -> username.equals(user.getUsername())).findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.addTaskToList(task);
        } else {
            log.warn("No such user");
        }
    }

}
