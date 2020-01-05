package org.elsys.quiz.dao;

import org.elsys.quiz.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return jdbcTemplate.query("SELECT * FROM Quizzes", () ->{

        });
    }
}
