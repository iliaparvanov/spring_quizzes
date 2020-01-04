package org.elsys.quiz.api;

import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/quizzes")
@RestController
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping(path = "{id}")
    public Quiz getQuizById(@PathVariable("id") int id) {
        return quizService.getQuizById(id).orElse(null);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllPeople();
    }
}
