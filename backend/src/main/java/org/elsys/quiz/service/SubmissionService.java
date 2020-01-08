package org.elsys.quiz.service;

import org.elsys.quiz.dao.QuizDao;
import org.elsys.quiz.dao.SubmissionDao;
import org.elsys.quiz.models.Quiz;
import org.elsys.quiz.models.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {
    private final SubmissionDao submissionDao;

    @Autowired
    public SubmissionService(@Qualifier("submission_h2") SubmissionDao submissionDao) {
        this.submissionDao = submissionDao;
    }

//    public List<Submission> getSubmissions(){
//        return submissionDao.getSubmissions();
//    }
//
//    public List<Submission> getSubmissions(int quizId) {return submissionDao.getSubmissions(quizId);}

    public Optional<Submission> getSubmissionById(int id) { return submissionDao.getSubmissionById(id); }

    public boolean addSubmission(Submission submission) { return submissionDao.addSubmission(submission); }
}
