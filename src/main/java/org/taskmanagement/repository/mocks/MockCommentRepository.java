package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.CommentRepository;



@Repository
@RequiredArgsConstructor
public class MockCommentRepository implements CommentRepository {

    static public Comment COMMENT_1 = new Comment(1,"best gyros of my life");

    @Override
    public void addComment(Comment comment) {

    }
}
