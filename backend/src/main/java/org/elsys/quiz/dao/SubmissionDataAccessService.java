package org.elsys.quiz.dao;

import org.elsys.quiz.models.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("h2")
public class SubmissionDataAccessService implements SubmissionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubmissionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return null;
    }
}
