package org.elsys.quiz.api;

import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.models.Submission;
import org.elsys.quiz.service.QuizService;
import org.elsys.quiz.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/submissions")
@RestController
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @PostMapping
    public void addSubmission() {
        
    }
}
