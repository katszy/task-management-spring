
-- Inserting projects (key aspects of the holiday planning)
INSERT INTO projects (title) VALUES
                                 ('Accommodation Arrangements'),
                                 ('Travel Itinerary'),
                                 ('Cultural Activities'),
                                 ('Dining and Cuisine'),
                                 ('Beach and Leisure');

-- Inserting tasks for each project
-- Note: Assigning tasks to users and projects by their IDs (IDs are assumed here, adjust as needed)
INSERT INTO tasks (title, due_date, status, priority, project_id, user_id) VALUES
                                                                               ('Research hotels in Athens', '2023-05-10', 'PLANNED', 1, 1, 1),
                                                                               ('Book flights to Mykonos', '2023-05-12', 'PLANNED', 2, 2, 2),
                                                                               ('Select cultural tours in Santorini', '2023-05-15', 'PLANNED', 3, 3, 3),
                                                                               ('Plan traditional Greek dining experiences', '2023-05-17', 'PLANNED', 4, 4, 4),
                                                                               ('Choose top beaches in Crete', '2023-05-20', 'PLANNED', 5, 5, 1),
                                                                               ('Arrange transportation in Athens', '2023-05-22', 'PLANNED', 1, 1, 2),
                                                                               ('Schedule vineyard visit in Santorini', '2023-05-25', 'PLANNED', 3, 3, 3),
                                                                               ('Book scuba diving in Corfu', '2023-05-27', 'PLANNED', 5, 5, 4),
                                                                               ('Organize a Greek cooking class', '2023-05-30', 'PLANNED', 4, 4, 1),
                                                                               ('Confirm hotel bookings', '2023-06-02', 'PLANNED', 1, 1, 2);

-- Inserting comments on tasks
-- Note: Comments are linked to tasks by task IDs (IDs are assumed here, adjust as needed)
INSERT INTO comments (text, task_id) VALUES
                                         ('Found several great hotel options in Plaka', 1),
                                         ('Flight booked! Excited!', 2),
                                         ('How about adding a pottery workshop?', 3),
                                         ('We should try a seafood taverna', 4),
                                         ('Elafonissi beach is a top pick', 2),
                                         ('Transportation is all set', 1);
