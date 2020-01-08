package org.elsys.quiz.service;

import org.elsys.quiz.dao.QuizDao;
import org.elsys.quiz.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizDao quizDao;

    @Autowired
    public QuizService(@Qualifier("quiz_h2") QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public List<Quiz> getAllQuizzes(){
        return quizDao.getAllQuizzes();
    }

}
