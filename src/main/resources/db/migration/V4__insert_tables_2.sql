CREATE TABLE project_user (
                              project_id INTEGER NOT NULL,
                              user_id INTEGER NOT NULL,
                              PRIMARY KEY (project_id, user_id),
                              FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
