CREATE TABLE results (
id INT AUTO_INCREMENT PRIMARY KEY,
question_ref INT NOT NULL,
answer CHAR NOT NULL,
user_id INT NOT NULL
);

INSERT INTO results (question_ref, answer, user_id) VALUES
(1, 'a', 1),
(2, 'b', 1),
(3, 'c', 1),
(4, 'd', 1),
(5, 'a', 1),
(6, 'b', 2),
(7, 'c', 2),
(8, 'd', 2),
(9, 'a', 2),
(10, 'b', 2),
(11, 'c', 3),
(1, 'd', 3),
(2, 'a', 3),
(3, 'b', 3),
(4, 'c', 3),
(5, 'd', 4),
(6, 'a', 4),
(7, 'b', 4),
(8, 'c', 4),
(9, 'd', 4),
(10, 'a', 5),
(11, 'b', 5),
(1, 'c', 5),
(2, 'd', 5),
(3, 'a', 5),
(4, 'b', 6),
(5, 'c', 6),
(6, 'd', 6),
(7, 'a', 6),
(8, 'b', 6),
(9, 'c', 7),
(10, 'd', 7),
(11, 'a', 7),
(1, 'b', 7),
(2, 'c', 7),
(3, 'd', 8),
(4, 'a', 8),
(5, 'b', 8),
(6, 'c', 8),
(7, 'd', 8),
(8, 'a', 9),
(9, 'b', 9),
(10, 'c', 9),
(11, 'd', 9),
(1, 'a', 10),
(2, 'b', 10),
(3, 'c', 10),
(4, 'd', 10),
(5, 'a', 10);
