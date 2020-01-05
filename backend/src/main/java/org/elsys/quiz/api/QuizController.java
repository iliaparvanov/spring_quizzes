package org.elsys.quiz.api;

import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/quizzes")
@RestController
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }
}
