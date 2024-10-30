CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    age INT NOT NULL,
    address VARCHAR(50) DEFAULT NULL,
    date_of_join DATE DEFAULT CURRENT_DATE
);

INSERT INTO users (name, surname, email, age, address) VALUES
('Noam', 'Cohen', 'noam.cohen@gmail.com', 28, 'Herzl St 15, Tel Aviv'),
('Maya', 'Levi', 'maya.levi@yahoo.com', 32, 'Dizengoff St 80, Tel Aviv'),
('Yossi', 'Ben-David', 'yossi.bendavid@hotmail.com', 45, 'Jabotinsky St 10, Ramat Gan'),
('Shira', 'Mizrahi', 'shira.mizrahi@gmail.com', 29, 'Haneviim St 25, Jerusalem'),
('Amit', 'Peretz', 'amit.peretz@gmail.com', 34, 'Kibbutz Galuyot St 120, Haifa'),
('Tamar', 'Shapiro', 'tamar.shapiro@gmail.com', 31, 'Weizmann St 55, Rehovot'),
('David', 'Friedman', 'david.friedman@gmail.com', 42, 'Rothschild Blvd 1, Tel Aviv'),
('Roni', 'Yitzhak', 'roni.yitzhak@yahoo.com', 27, 'Allenby St 90, Tel Aviv'),
('Gal', 'Shalom', 'gal.shalom@gmail.com', 36, 'Bialik St 3, Rishon LeZion'),
('Yael', 'Katz', 'yael.katz@hotmail.com', 30, 'Hertzel Blvd 75, Beer Sheva');
