package org.elsys.quiz.dao;

import org.elsys.quiz.models.Submission;

import java.util.List;

public interface SubmissionDao {
    List<Submission> getAllSubmissions();
}
