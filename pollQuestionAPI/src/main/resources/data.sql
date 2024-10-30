CREATE TABLE questions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255) NOT NULL,
    answer_a VARCHAR(255) NOT NULL,
    answer_b VARCHAR(255) NOT NULL,
    answer_c VARCHAR(255) NOT NULL,
    answer_d VARCHAR(255) NOT NULL
);

INSERT INTO questions (question, answer_a, answer_b, answer_c, answer_d) VALUES
('What is your favorite color?', 'Red', 'Blue', 'Green', 'Yellow'),
('What is your dream job?', 'Software Engineer', 'Doctor', 'Artist', 'Entrepreneur'),
('Which city would you like to visit?', 'New York', 'Paris', 'Tokyo', 'Sydney'),
('What is your favorite season?', 'Spring', 'Summer', 'Autumn', 'Winter'),
('What is your favorite type of cuisine?', 'Italian', 'Mexican', 'Chinese', 'Indian'),
('Which pet would you prefer to have?', 'Dog', 'Cat', 'Bird', 'Fish'),
('What is your favorite genre of music?', 'Rock', 'Pop', 'Jazz', 'Classical'),
('Which sport do you enjoy the most?', 'Soccer', 'Basketball', 'Tennis', 'Swimming'),
('What is your ideal vacation type?', 'Beach', 'Mountain', 'City', 'Countryside'),
('Which social media platform do you use the most?', 'Facebook', 'Instagram', 'Twitter', 'LinkedIn'),
('Which is your favorite language?', 'C', 'Java', 'Python', 'bash');