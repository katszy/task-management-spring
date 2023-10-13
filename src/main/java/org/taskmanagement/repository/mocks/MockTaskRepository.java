package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

import static org.taskmanagement.repository.mocks.MockUserRepository.USERS;

@Repository
@RequiredArgsConstructor
public class MockTaskRepository implements TaskRepository {
    public static Task TASK_1  = new Task(1,
            "Research gyros places",
            LocalDate.of(2023, 10, 13),
            "started",
            1,
            USERS,
            null
    );
}
