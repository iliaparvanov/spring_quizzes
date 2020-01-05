package org.elsys.quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Submission {
    private final int id;
    private final int score;
    private final int quizId;
    private final List<Answer> answers;

    public Submission(@JsonProperty("id") int id, @JsonProperty("score") int score, @JsonProperty("quiz_id") int quizId, List<Answer> answers) {
        this.id = id;
        this.score = score;
        this.quizId = quizId;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getQuizId() {
        return quizId;
    }
}
