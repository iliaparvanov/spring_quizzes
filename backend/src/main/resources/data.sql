DROP TABLE IF EXISTS Quizzes;
DROP TABLE IF EXISTS Questions;
DROP TABLE IF EXISTS Answers;
DROP TABLE IF EXISTS Submissions;

CREATE TABLE Quizzes (
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL
);

CREATE TABLE Questions (
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Text VARCHAR(100) NOT NULL,
    QuizId INT NOT NULL,

    FOREIGN KEY (QuizId) REFERENCES Quizzes(Id)
);

CREATE TABLE Answers (
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Text VARCHAR(100) NOT NULL,
    IsCorrect BOOLEAN NOT NULL,
    QuestionId INT NOT NULL,

    FOREIGN KEY (QuestionId) REFERENCES Questions(Id)
);

CREATE TABLE Submissions (
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Score INT NOT NULL,
    QuizId INT NOT NULL,

    FOREIGN KEY (QuizId) REFERENCES Quizzes(Id)
);

INSERT INTO Quizzes VALUES (1, 'Biology');

INSERT INTO Questions VALUES (1, 'Which famous scientist introduced the idea of natural selection?', 1);
INSERT INTO Answers VALUES (1, 'Charles Darwin', TRUE, 1);
INSERT INTO Answers VALUES (2, 'Pesho Peshev', FALSE, 1);
INSERT INTO Answers VALUES (3, 'Isaac Newton', FALSE, 1);
INSERT INTO Answers VALUES (4, 'Louis Pasteur', FALSE, 1);

INSERT INTO Questions VALUES (2, 'What are biologists interested in?', 1);
INSERT INTO Answers VALUES (5, 'flora', TRUE, 2);
INSERT INTO Answers VALUES (6, 'music', FALSE, 2);
INSERT INTO Answers VALUES (7, 'fauna', TRUE, 2);
INSERT INTO Answers VALUES (8, 'literature', FALSE, 2);
