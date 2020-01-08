package org.elsys.quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Submission {
    private int id;
    private int scoredPoints;
    private int totalPoints;
    private final int quizId;
    private List<Answer> answers;

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setScoredPoints(int scoredPoints) {
        this.scoredPoints = scoredPoints;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Submission(@JsonProperty("id") int id, @JsonProperty("scored_points") int scoredPoints,
                      @JsonProperty("total_points") int totalPoints, @JsonProperty("quiz_id") int quizId,
                      @JsonProperty("answers") List<Answer> answers) {
        this.id = id;
        this.scoredPoints = scoredPoints;
        this.totalPoints = totalPoints;
        this.quizId = quizId;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public int getQuizId() {
        return quizId;
    }
}
