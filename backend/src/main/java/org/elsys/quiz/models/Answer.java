package org.elsys.quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private final int id;
    private final String text;

    public Answer(@JsonProperty("id") int id, @JsonProperty("text") String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
