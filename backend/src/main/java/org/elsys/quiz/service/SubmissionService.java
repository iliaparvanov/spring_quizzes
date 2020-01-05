package org.elsys.quiz.service;

import org.elsys.quiz.dao.QuizDao;
import org.elsys.quiz.dao.SubmissionDao;
import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.models.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {
    private final SubmissionDao submissionDao;

    @Autowired
    public SubmissionService(@Qualifier("h2") SubmissionDao submissionDao) {
        this.submissionDao = submissionDao;
    }

    public List<Submission> getSubmissions(){
        return submissionDao.getSubmissions();
    }

    public List<Submission> getSubmissions(int quizId) {return submissionDao.getSubmissions(quizId);}

    public boolean addSubmission(Submission submission) { return submissionDao.addSubmission(submission); }
}
