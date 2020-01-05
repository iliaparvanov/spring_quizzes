package org.elsys.quiz.dao;

import org.elsys.quiz.models.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizDao {
    List<Quiz> getAllQuizzes();
}
