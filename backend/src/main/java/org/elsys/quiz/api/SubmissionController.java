package org.elsys.quiz.api;

import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.models.Submission;
import org.elsys.quiz.service.QuizService;
import org.elsys.quiz.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping
    public void addSubmission(@Valid @NonNull @RequestBody Submission submission) {
        submissionService.addSubmission(submission);
        //TODO: ADD ERROR RESPONSE IN CASE SUBMISSION WAS INVALID (EG. QUIZ_ID WAS INVALID)
    }
}
