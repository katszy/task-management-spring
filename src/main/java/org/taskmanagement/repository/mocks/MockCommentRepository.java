package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.CommentRepository;

import static org.taskmanagement.repository.mocks.MockUserRepository.USERS;


@Repository
@RequiredArgsConstructor
public class MockCommentRepository implements CommentRepository {

    static public Comment COMMENT_1 = new Comment(1,"best gyros of my life",new User(2, "Ed", "ed@example.com", "pw", "user"));
}
