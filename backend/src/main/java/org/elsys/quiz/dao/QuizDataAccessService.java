package org.elsys.quiz.dao;

import org.elsys.quiz.models.Answer;
import org.elsys.quiz.models.Question;
import org.elsys.quiz.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("h2")
public class QuizDataAccessService implements QuizDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuizDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Quiz> getQuizById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Quiz> getAllQuizzes() {

        List<Quiz> quizzes = this.jdbcTemplate.query(
                "SELECT Id, Name FROM Quizzes",
                (rs, rowNum) -> {
                    Quiz quiz = new Quiz(rs.getInt("Id"), rs.getString("Name"), new ArrayList<Question>());
                    return quiz;
                });

        for (Quiz quiz : quizzes) {
            List<Question> questions = this.jdbcTemplate.query("SELECT Id, Text FROM Questions WHERE QuizId = ?", new Object[]{quiz.getId()}, (rs, rowNum) -> {
                Question question = new Question(rs.getInt("Id"), rs.getString("Text"), new ArrayList<Answer>());
                return question;
            });

            for (Question question : questions) {
                List<Answer> answers = this.jdbcTemplate.query("SELECT Id, Text FROM Answers WHERE QuestionId = ?", new Object[]{question.getId()}, (rs, rowNum) -> {
                    Answer answer = new Answer(rs.getInt("Id"), rs.getString("Text"));
                    return answer;
                });
                question.setAnswers(answers);
            }
            quiz.setContent(questions);
        }

        return quizzes;
    }
}
