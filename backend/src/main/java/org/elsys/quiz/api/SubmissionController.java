package org.elsys.quiz.api;

import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.models.Submission;
import org.elsys.quiz.service.QuizService;
import org.elsys.quiz.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5000")
@RequestMapping("api/v1/submissions")
@RestController
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping(path="{id}")
    public Submission getSubmissionById(@PathVariable("id") int id) {
        return submissionService.getSubmissionById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Object> addSubmission(@Valid @NonNull @RequestBody Submission submission) {
        if (submissionService.addSubmission(submission)) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(submission.getId())
                    .toUri();
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setLocation(location);
//            httpHeaders.set("Access-Control-Expose-Headers", "*");
            return ResponseEntity.created(location).header("Access-Control-Expose-Headers", "Location").build();
//            return new ResponseEntity\<Object>()
        } else {
            return ResponseEntity.badRequest().build();
        }

        //TODO: ADD ERROR RESPONSE IN CASE SUBMISSION WAS INVALID (EG. QUIZ_ID WAS INVALID)
    }
}
