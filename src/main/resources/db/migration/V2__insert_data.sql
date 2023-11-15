-- Inserting users (participants planning the Girls' Greek Holiday, with one admin)
INSERT INTO users (username, email, password, role) VALUES
                                                        ('Elena', 'elena@example.com', 'pass123', 'ROLE_USER'),
                                                        ('Maria', 'maria@example.com', 'pass456', 'ROLE_USER'),
                                                        ('Sophia', 'sophia@example.com', 'pass789', 'ROLE_USER'),
                                                        ('Zoe', 'zoe@example.com', 'pass101', 'ROLE_ADMIN'); -- Zoe as the admin
