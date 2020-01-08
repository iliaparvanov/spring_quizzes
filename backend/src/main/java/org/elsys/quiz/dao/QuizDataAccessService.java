package org.elsys.quiz.dao;

import org.elsys.quiz.models.Answer;
import org.elsys.quiz.models.Question;
import org.elsys.quiz.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("quiz_h2")
public class QuizDataAccessService implements QuizDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuizDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
