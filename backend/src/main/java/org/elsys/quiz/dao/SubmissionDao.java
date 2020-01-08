package org.elsys.quiz.dao;

import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.models.Submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionDao {
//    List<Submission> getSubmissions();
//    List<Submission> getSubmissions(int quizId);
    Optional<Submission> getSubmissionById(int id);
    boolean addSubmission(Submission submission);
}
