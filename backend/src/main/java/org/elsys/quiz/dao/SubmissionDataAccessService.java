package org.elsys.quiz.dao;

import org.elsys.quiz.models.Answer;
import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.models.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("h2")
public class SubmissionDataAccessService implements SubmissionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubmissionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Submission> getSubmissions() {
        List<Submission> submissions = this.jdbcTemplate.query("SELECT * FROM Submissions",
                (rs, rowNum) -> {
                    Submission submission = new Submission(rs.getInt("Id"), 0, rs.getInt("QuizId"), new ArrayList<Answer>());
                    return submission;
                });

        for (Submission submission : submissions) {
            List<Answer> answers = this.jdbcTemplate.query(
                    "SELECT * FROM SubmittedAnswers as sa\n" +
                    "RIGHT JOIN Answers as a ON sa.AnswerId = a.Id\n" +
                    "WHERE sa.AnswerId IS NOT NULL AND sa.SubmissionId = ?", new Object[]{submission.getId()},
            (rs, rowNum) -> {
                return new Answer(rs.getInt("Id"), rs.getString("Text"));
            });
            submission.setAnswers(answers);

            int correctAnswers = this.jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM SubmittedAnswers as sa\n" +
                    "RIGHT JOIN Answers as a ON sa.AnswerId = a.Id\n" +
                    "WHERE sa.AnswerId IS NOT NULL AND a.IsCorrect = TRUE AND sa.SubmissionId = ?",
                    new Object[]{submission.getId()},
                    Integer.class);
            submission.setScore(correctAnswers);


        }

        return submissions;
    }

    @Override
    public List<Submission> getSubmissions(int quizId) {
        return null;
    }

    @Override
    public boolean addSubmission(Submission submission) {
        if (jdbcTemplate.update("INSERT INTO Submissions (QuizId) VALUES (?)", submission.getQuizId()) < 1) {
            return false;
        }
        for (Answer answer : submission.getAnswers()) {
            if (jdbcTemplate.update("INSERT INTO SubmittedAnswers VALUE (?, ?)", submission.getId(), answer.getId()) < 1) {
                return false;
            }
        }
        return true;
    }
}
