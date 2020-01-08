package org.elsys.quiz.dao;

import org.elsys.quiz.models.Answer;
import org.elsys.quiz.models.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("submission_h2")
public class SubmissionDataAccessService implements SubmissionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubmissionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public List<Submission> getSubmissions() {
//        List<Submission> submissions = this.jdbcTemplate.query("SELECT * FROM Submissions",
//                (rs, rowNum) -> {
//                    Submission submission = new Submission(rs.getInt("Id"), 0, totalPoints, rs.getInt("QuizId"), new ArrayList<Answer>());
//                    return submission;
//                });
//
//        for (Submission submission : submissions) {
//            List<Answer> answers = this.jdbcTemplate.query(
//                    "SELECT * FROM SubmittedAnswers as sa\n" +
//                    "RIGHT JOIN Answers as a ON sa.AnswerId = a.Id\n" +
//                    "WHERE sa.AnswerId IS NOT NULL AND sa.SubmissionId = ?", new Object[]{submission.getId()},
//            (rs, rowNum) -> {
//                return new Answer(rs.getInt("Id"), rs.getString("Text"));
//            });
//            submission.setAnswers(answers);
//
//            int correctAnswers = this.jdbcTemplate.queryForObject(
//                    "SELECT COUNT(*) FROM SubmittedAnswers as sa\n" +
//                    "RIGHT JOIN Answers as a ON sa.AnswerId = a.Id\n" +
//                    "WHERE sa.AnswerId IS NOT NULL AND a.IsCorrect = TRUE AND sa.SubmissionId = ?",
//                    new Object[]{submission.getId()},
//                    Integer.class);
//            submission.setScoredPoints(correctAnswers);
//
//
//        }
//
//        return submissions;
//    }
//
//    @Override
//    public List<Submission> getSubmissions(int quizId) {
//        return null;
//    }

    @Override
    public Optional<Submission> getSubmissionById(int id) {

        //first check if id actually exists
        Submission submission = this.jdbcTemplate.queryForObject("SELECT * FROM Submissions WHERE Id = ?", new Object[]{id},
                (rs, rowNum) -> {
                    Submission innerSubmission = new Submission(rs.getInt("Id"), 0, 0,
                            rs.getInt("QuizId"), new ArrayList<Answer>());
                    //get answers
                    List<Answer> answers = this.jdbcTemplate.query(
                    "SELECT a.Id AS Id, a.Text AS Text FROM SubmittedAnswers as sa\n" +
                        "INNER JOIN Answers as a ON sa.AnswerId = a.Id\n" +
                        "WHERE sa.AnswerId IS NOT NULL AND sa.SubmissionId = ?", new Object[]{innerSubmission.getId()},
                    (ars, aRowNum) -> {
                        return new Answer(ars.getInt("Id"), ars.getString("Text"));
                    });
                    innerSubmission.setAnswers(answers);

                    //calculate score
                    int score = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM SubmittedAnswers AS sa\n" +
                            "INNER JOIN Answers AS a ON a.Id = sa.AnswerId\n" +
                            "WHERE SubmissionId = ? AND a.IsCorrect = TRUE", new Object[]{innerSubmission.getId()}, Integer.class);
                    innerSubmission.setScoredPoints(score);

                    int total = jdbcTemplate.queryForObject("SELECT Count(*) FROM Questions as q\n" +
                            "INNER JOIN Answers as a ON q.Id = a.QuestionId\n" +
                            "WHERE q.QuizId = ? AND a.IsCorrect = TRUE;", new Object[]{innerSubmission.getQuizId()}, Integer.class);
                    innerSubmission.setTotalPoints(total);

                    System.out.println("TOTAL POINTS: " + innerSubmission.getTotalPoints());
                    return innerSubmission;
                });
        return Optional.ofNullable(submission);
    }

    @Override
    public boolean addSubmission(Submission submission) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO Submissions (QuizId) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, submission.getQuizId());
            return ps;
        }, keyHolder) < 1) {
            return false;
        }

        for (Answer answer : submission.getAnswers()) {
            //SUBMISSION ID BELOW IS 0, HOW TO FIX THIS???
            if (jdbcTemplate.update("INSERT INTO SubmittedAnswers VALUES (?, ?)", keyHolder.getKey(), answer.getId()) < 1) {
                return false;
            }
        }
        submission.setId((int) keyHolder.getKey());
        return true;
    }
}
