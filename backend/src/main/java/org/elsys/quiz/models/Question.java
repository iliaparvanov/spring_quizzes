package org.elsys.quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Question {
    private final int id;
    private final String text;
    private List<Answer> answers;

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question(@JsonProperty ("id") int id, @JsonProperty("text") String text, @JsonProperty("answers") List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
