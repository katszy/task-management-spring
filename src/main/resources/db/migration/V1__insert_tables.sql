
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255)
);

CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          title VARCHAR(255) NOT NULL
);

CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       due_date DATE,
                       status VARCHAR(255),
                       priority INTEGER,
                       project_id INTEGER REFERENCES projects(id),
                       user_id INTEGER REFERENCES users(id)
);

CREATE TABLE comments (
                          id SERIAL PRIMARY KEY,
                          text TEXT NOT NULL,
                          task_id INTEGER REFERENCES tasks(id)
);
