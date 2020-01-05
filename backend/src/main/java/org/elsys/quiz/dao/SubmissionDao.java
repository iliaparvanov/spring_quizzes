package org.elsys.quiz.dao;

import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.models.Submission;

import java.util.List;

public interface SubmissionDao {
    List<Submission> getSubmissions();
    List<Submission> getSubmissions(int quizId);
    boolean addSubmission(Submission submission);
}
