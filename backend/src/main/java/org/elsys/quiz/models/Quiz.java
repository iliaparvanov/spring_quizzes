package org.elsys.quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Quiz {
    private final int id;
    private final String name;
    int avgCorrectAnswers;
    int totalCorrectAnswers;
    private List<Question> content;

    public Quiz(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("avg_correct_answers") int avgCorrectAnswers,
                @JsonProperty("total_correct_answers") int totalCorrectAnswers, @JsonProperty("content") List<Question> content) {
        this.id = id;
        this.name = name;
        this.avgCorrectAnswers = avgCorrectAnswers;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.content = content;
    }

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public int getAvgCorrectAnswers() {
        return avgCorrectAnswers;
    }

    public List<Question> getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setContent(List<Question> content) {
        this.content = content;
    }

    public void setAvgCorrectAnswers(int avgCorrectAnswers) { this.avgCorrectAnswers = avgCorrectAnswers; }

    public void setTotalCorrectAnswers(int totalCorrectAnswers) {
        this.totalCorrectAnswers = totalCorrectAnswers;
    }
}
