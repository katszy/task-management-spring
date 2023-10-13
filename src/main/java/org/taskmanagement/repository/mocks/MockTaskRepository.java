package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.taskmanagement.repository.TaskRepository;

@Repository
@RequiredArgsConstructor
public class MockTaskRepository implements TaskRepository {
}
